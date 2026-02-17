package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.common.LoadTodayDrinkUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.DailyDrink
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.DailyDrinkUseCase
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.model.ResettableUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DailyDrinkViewModel @Inject constructor(
    private val dailyDrinkUseCase: DailyDrinkUseCase,
    private val loadTodayDrinkUseCase: LoadTodayDrinkUseCase,

    ) : ViewModel() {

    companion object {
        private const val TAG = "DailyDrinkViewModel"
    }

    // 📅 آب امروز
    private val _todayDrink = MutableStateFlow<DailyDrink?>(null)
    val todayDrink: StateFlow<DailyDrink?> = _todayDrink

    // 🎯 هدف آب امروز
    private val _dailyNeed = MutableStateFlow(0)
    val dailyNeed: StateFlow<Int> = _dailyNeed

    private val _uiState = MutableStateFlow(ResettableUiState())
    val uiState: StateFlow<ResettableUiState> = _uiState.asStateFlow()

    init {
        loadToday()
    }

    private fun loadToday() {
        viewModelScope.launch {
            val (drink, target) = loadTodayDrinkUseCase()
            _todayDrink.value = drink
            _dailyNeed.value = target
        }
    }

    fun addAmount(amount: Int) {
        viewModelScope.launch {
            val today = LocalDate.now().toString()
            val updated =
                dailyDrinkUseCase.addAmountToDailyDrinkUseCase(today, amount)
            _todayDrink . value = updated
                    if (updated.totalDrink >= _dailyNeed.value) {
                        dailyDrinkUseCase.setDayCompletedUseCase(today, true)
                    }
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            _uiState.value = ResettableUiState(isLoading = true)
            try {
                dailyDrinkUseCase.clearAll()
                _todayDrink.value = null
                _dailyNeed.value = 0
                _uiState.value = ResettableUiState(isDone = true)
            } catch (e: Exception) {
                Log.e(TAG, "Error clearing daily drinks", e)
                _uiState.value = ResettableUiState(error = e.message)
            }
        }
    }

    fun resetToday() {
        viewModelScope.launch {
            val today = LocalDate.now().toString()
            val resetDrink = dailyDrinkUseCase.resetTodayDrinkUseCase(today)
            _todayDrink.value = resetDrink
        }
    }
}
