package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irajnajafi1988gmail.hydrobell.domain.datastore.model.DishesUseCase
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.model.ItemDishes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DishesViewModel @Inject constructor(
    private val useCase: DishesUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "DishesViewModel"
        val DEFAULT_DISH = ItemDishes()
    }

    private val _showDishes = MutableStateFlow(false)
    val showDishes = _showDishes.asStateFlow()

    fun toggleDishes() {
        _showDishes.value = !_showDishes.value
        Log.d(TAG, "وضعیت نمایش ظروف: ${_showDishes.value}")
    }

    val selectedDish: StateFlow<ItemDishes> =
        useCase.getSelectedDishUseCase()
            .stateIn(
                viewModelScope,
                SharingStarted.Companion.WhileSubscribed(5_000),
                DEFAULT_DISH
            )

    fun selectDish(dish: ItemDishes) {
        viewModelScope.launch {
            try {
                useCase.saveDishesUseCase(dish)
            } catch (e: Exception) {
                Log.e(TAG, "خطا در ذخیره ظرف", e)
            }
        }
    }
    fun closeDishes() {
        _showDishes.value = false
    }


    fun resetDishes() {
        viewModelScope.launch {
            useCase.restartDishesUseCase()
            _showDishes.value = false
        }
    }
}