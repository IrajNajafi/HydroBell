package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.common.LoadTodayDrinkUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.DailyDrink
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.GetAllUseCase
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.model.ChartBarData
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.model.HistoryRange
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.utils.AppDate
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.utils.formatMonthTitle
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.utils.formatWeekTitle
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.utils.formatYearTitle
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.utils.generateMonthData
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.utils.generateWeekData
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.utils.generateYearData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getAllUseCase: GetAllUseCase,
    private val loadTodayDrinkUseCase: LoadTodayDrinkUseCase,
    private val appDate: AppDate,
) : ViewModel() {

    // -------------------- Constants --------------------
    private val today: LocalDate
        get() = LocalDate.now()

    private val minDate: LocalDate =
        LocalDate.of(2020, 1, 1)

    // -------------------- States --------------------
    private val _todayDrink = MutableStateFlow<DailyDrink?>(null)
    val todayDrink: StateFlow<DailyDrink?> = _todayDrink.asStateFlow()

    private val _dailyTarget = MutableStateFlow(0)
    val dailyTarget: StateFlow<Int> = _dailyTarget.asStateFlow()

    private val _referenceDate = MutableStateFlow(today)
    val referenceDate: StateFlow<LocalDate> = _referenceDate.asStateFlow()

    private val _range = MutableStateFlow(HistoryRange.WEEK)
    val range: StateFlow<HistoryRange> = _range.asStateFlow()

    // -------------------- Title --------------------
    val title: StateFlow<String> =
        combine(referenceDate, range) { date, range ->
            when (range) {
                HistoryRange.WEEK -> formatWeekTitle(date)
                HistoryRange.MONTH -> formatMonthTitle(date)
                HistoryRange.YEAR -> formatYearTitle(date)
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            ""
        )

    // -------------------- Chart Data --------------------
    val chartData: StateFlow<List<ChartBarData>> =
        combine(
            getAllUseCase(),
            range,
            referenceDate,
            dailyTarget
        ) { allData, range, refDate, target ->

            if (target <= 0) return@combine emptyList()

            val drinksByDate = allData.associateBy {
                LocalDate.parse(it.date)
            }

            when (range) {
                HistoryRange.WEEK ->
                    generateWeekData(drinksByDate, refDate, target)

                HistoryRange.MONTH ->
                    generateMonthData(drinksByDate, refDate, target)

                HistoryRange.YEAR ->
                    generateYearData(drinksByDate, refDate, target)
            }

        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            emptyList()
        )

    // -------------------- Init --------------------
    init {
        loadInitialData()
    }

    // -------------------- Loaders --------------------
    private fun loadInitialData() {
        viewModelScope.launch {
            val (todayDrink, target) = loadTodayDrinkUseCase()
            _todayDrink.value = todayDrink
            _dailyTarget.value = target
        }
    }

    // -------------------- Actions --------------------
    fun changeRange(newRange: HistoryRange) {
        _range.value = newRange
        _referenceDate.value = today
    }

    fun changeReferenceDate(newDate: LocalDate) {
        _referenceDate.value = newDate
    }

    fun moveNext() {
        if (!canGoNext.value) return

        _referenceDate.value = when (range.value) {
            HistoryRange.WEEK -> referenceDate.value.plusWeeks(1)
            HistoryRange.MONTH -> referenceDate.value.plusMonths(1)
            HistoryRange.YEAR -> referenceDate.value.plusYears(1)
        }
    }

    fun movePrevious() {
        if (!canGoPrevious.value) return

        _referenceDate.value = when (range.value) {
            HistoryRange.WEEK -> referenceDate.value.minusWeeks(1)
            HistoryRange.MONTH -> referenceDate.value.minusMonths(1)
            HistoryRange.YEAR -> referenceDate.value.minusYears(1)
        }
    }

    // -------------------- Navigation Guards --------------------
    val canGoNext: StateFlow<Boolean> =
        combine(referenceDate, range) { date, range ->
            when (range) {
                HistoryRange.WEEK -> date.plusWeeks(1).isBefore(today.plusDays(1))
                HistoryRange.MONTH -> date.plusMonths(1).isBefore(today.plusDays(1))
                HistoryRange.YEAR -> date.plusYears(1).isBefore(today.plusDays(1))
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            false
        )

    val canGoPrevious: StateFlow<Boolean> =
        referenceDate
            .map { it.isAfter(minDate) }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                false
            )
}
