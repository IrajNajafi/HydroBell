package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irajnajafi1988gmail.hydrobell.domain.datastore.model.LanguageUseCase
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.model.ItemLanguage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val useCase: LanguageUseCase
): ViewModel() {

    val selectedLanguage: StateFlow<ItemLanguage?> = useCase.getLanguageStateUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun saveLanguage(language: ItemLanguage) {
        viewModelScope.launch {
            useCase.saveLanguageStateUseCase(language)
        }
    }
}