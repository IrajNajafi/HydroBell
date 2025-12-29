package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.common.LoadTodayDrinkUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.DailyDrink
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.DailyDrinkUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.UserProfileUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.GetAllUseCase
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.model.ChartBarData
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.model.HistoryRange
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.utils.formatMonthTitle
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.utils.formatWeekTitle
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.utils.formatYearTitle
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.utils.generateMonthData
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.utils.generateWeekData
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.utils.generateYearData
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.utils.WaterCalculatorDynamic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getAllUseCase: GetAllUseCase,
    private val loadTodayDrinkUseCase: LoadTodayDrinkUseCase

) : ViewModel() {

    // ----------------------------- 1️⃣ State Variables -----------------------------
    // Holds today's drink data
    private val _todayDrink = MutableStateFlow<DailyDrink?>(null)
    val todayDrink: StateFlow<DailyDrink?> = _todayDrink.asStateFlow()

    // Anchor date for history chart (week/month/year)
    private val _anchorDate = MutableStateFlow(LocalDate.now())
    val anchorDate: StateFlow<LocalDate> = _anchorDate.asStateFlow()

    // Current range selection (Week / Month / Year)
    private val _range = MutableStateFlow(HistoryRange.WEEK)
    val range: StateFlow<HistoryRange> = _range.asStateFlow()

    // Daily water target in ml
    private val _dailyTarget = MutableStateFlow(0)
    val dailyTarget: StateFlow<Int> = _dailyTarget.asStateFlow()

    // Chart data based on range and drinks
    private val _chartData = MutableStateFlow<List<ChartBarData>>(emptyList())
    val chartData: StateFlow<List<ChartBarData>> = _chartData.asStateFlow()


    // ----------------------------- 2️⃣ Title Computation -----------------------------
    // Title string depending on selected range and anchor date
    val title: StateFlow<String> =
        combine(anchorDate, range) { date, range ->
            when (range) {
                HistoryRange.WEEK -> formatWeekTitle(date)
                HistoryRange.MONTH -> formatMonthTitle(date)
                HistoryRange.YEAR -> formatYearTitle(date)
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "")


    // ----------------------------- 3️⃣ Initialization & Loaders -----------------------------
    init {
        loadDailyTarget()  // Load today's drink and daily target
        observeAllData()   // Observe database changes
    }

    // Observe database changes and update chart automatically
    private fun observeAllData() {
        viewModelScope.launch {
            getAllUseCase().collect { allData ->
                updateChart(allData)
            }
        }
    }

    // Load daily target and today's drink from database
    private fun loadDailyTarget() {
        viewModelScope.launch {
            val (_, target) = loadTodayDrinkUseCase()
            _dailyTarget.value = target
        }
    }




    // ----------------------------- 4️⃣ Chart & Navigation Functions -----------------------------
    // Update chart based on range and drinks
    private fun updateChart(allData: List<DailyDrink>) {
        val target = _dailyTarget.value
        val drinksByDate = allData.associateBy { LocalDate.parse(it.date) }

        _chartData.value = when (_range.value) {
            HistoryRange.WEEK -> generateWeekData(drinksByDate, _anchorDate.value, target)
            HistoryRange.MONTH -> generateMonthData(drinksByDate, _anchorDate.value, target)
            HistoryRange.YEAR -> generateYearData(drinksByDate, _anchorDate.value, target)
        }
    }

    // Change history range and refresh chart
    fun changeRange(newRange: HistoryRange) {
        _range.value = newRange
        refreshChart()
    }

    // Move anchor date forward/backward
    fun moveNext() = adjustAnchorDate(1)
    fun movePrevious() = adjustAnchorDate(-1)

    private fun adjustAnchorDate(offset: Int) {
        _anchorDate.value = when (_range.value) {
            HistoryRange.WEEK -> _anchorDate.value.plusWeeks(offset.toLong())
            HistoryRange.MONTH -> _anchorDate.value.plusMonths(offset.toLong())
            HistoryRange.YEAR -> _anchorDate.value.plusYears(offset.toLong())
        }
        refreshChart()
    }

    // Refresh chart data once
    private fun refreshChart() {
        viewModelScope.launch {
            val allData = getAllUseCase().first()
            updateChart(allData)
        }
    }

    // ----------------------------- 5️⃣ Arrow Navigation States -----------------------------
    val canGoPrevious: StateFlow<Boolean> =
        combine(_anchorDate, _range) { date, _ -> date.isAfter(LocalDate.of(2020, 1, 1)) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    val canGoNext: StateFlow<Boolean> =
        combine(_anchorDate, _range) { date, _ -> date.isBefore(LocalDate.now()) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)
}
