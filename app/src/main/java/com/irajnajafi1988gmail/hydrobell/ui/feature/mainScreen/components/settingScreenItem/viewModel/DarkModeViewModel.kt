package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irajnajafi1988gmail.hydrobell.domain.datastore.model.DarkModeUseCase
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.model.ItemDarkMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DarkModeViewModel @Inject constructor(
    private val useCase: DarkModeUseCase
) : ViewModel() {

    val selectedDarkMode: StateFlow<ItemDarkMode?> = useCase.getDarkModeStateUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    fun saveDarkMode(mode: ItemDarkMode) {
        viewModelScope.launch {
            useCase.saveDarkModeStateUseCase(mode)
        }
    }
}
