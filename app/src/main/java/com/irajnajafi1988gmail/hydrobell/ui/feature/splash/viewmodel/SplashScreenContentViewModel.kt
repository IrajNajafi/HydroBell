package com.irajnajafi1988gmail.hydrobell.ui.feature.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irajnajafi1988gmail.hydrobell.domain.datastore.model.IsCompleteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenContentViewModel @Inject constructor(
    private val useCase: IsCompleteUseCase
) : ViewModel() {

    val isProfileComplete = useCase.getIsProfileCompleteUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )
}
