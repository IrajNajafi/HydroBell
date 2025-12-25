package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.DailyDrink
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.DailyDrinkUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.UserProfileUseCase
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.model.ResettableUiState
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.model.WaterCalculatorDynamic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class DailyDrinkViewModel @Inject constructor(
    private val dailyDrinkUseCase: DailyDrinkUseCase,
    private val userProfileUseCase: UserProfileUseCase
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
            try {
                val todayDate = LocalDate.now().toString()

                // 1️⃣ گرفتن پروفایل
                val profile = userProfileUseCase.getUserProfileUseCase()

                // 2️⃣ محاسبه هدف روزانه
                val goal = profile?.let {
                    WaterCalculatorDynamic.calculateDailyNeedMl(it)
                } ?: 0

                _dailyNeed.value = goal

                // 3️⃣ گرفتن آب امروز
                val todayData = dailyDrinkUseCase.getByDate(todayDate).first()

                // 4️⃣ اگر نبود، بساز
                _todayDrink.value = todayData ?: run {
                    val newDaily = DailyDrink(
                        date = todayDate,
                        totalDrink = 0
                    )
                    dailyDrinkUseCase.upsert(newDaily)
                    newDaily
                }

            } catch (e: Exception) {
                Log.e(TAG, "Error loading today data", e)
                _todayDrink.value = null
                _dailyNeed.value = 0
            }
        }
    }


    fun addAmount(amount: Int) {
        viewModelScope.launch {
            val today = LocalDate.now().toString()
            Log.d(TAG, "Adding amount: $amount ml to date: $today")

            val updated = dailyDrinkUseCase.addAmountToDailyDrinkUseCase(today, amount)

            _todayDrink.value = updated

            Log.d(TAG, "TodayDrink updated: $updated")
        }
    }


    fun clearAll() {
        viewModelScope.launch {
            _uiState.value = ResettableUiState(isLoading = true)

            try {
                dailyDrinkUseCase.clearAll()

                _todayDrink.value = null
                _dailyNeed.value = 0

                _uiState.value = ResettableUiState(
                    isDone = true
                )

            } catch (e: Exception) {
                Log.e(TAG, "Error clearing daily drinks", e)

                _uiState.value = ResettableUiState(
                    error = e.message
                )
            }
        }
    }

}
