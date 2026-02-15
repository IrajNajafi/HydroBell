package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irajnajafi1988gmail.hydrobell.domain.datastore.model.IsCompleteUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.UserProfileUseCase
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.model.ResettableUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val userProfileUseCase: UserProfileUseCase,
    private val isCompleteUseCase: IsCompleteUseCase,

    ) : ViewModel() {

    private val _uiState = MutableStateFlow(ResettableUiState())
    val uiState: StateFlow<ResettableUiState> = _uiState.asStateFlow()




    fun restartUserProfile() {
        viewModelScope.launch {
            _uiState.value = ResettableUiState(isLoading = true)

            try {

                userProfileUseCase.clearUserProfileUseCase()

                isCompleteUseCase.setProfileCompleteUseCase(false)

                delay(1000)

                _uiState.value = ResettableUiState(
                    isDone = true
                )

            } catch (e: Exception) {
                _uiState.value = ResettableUiState(
                    error = e.message
                )
            }
        }
    }
}