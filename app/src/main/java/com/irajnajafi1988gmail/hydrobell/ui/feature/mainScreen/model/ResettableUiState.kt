package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.model

data class ResettableUiState(
    val isLoading: Boolean = false,
    val isDone: Boolean = false,   // جای isResetDone یا isCleared
    val error: String? = null
)
