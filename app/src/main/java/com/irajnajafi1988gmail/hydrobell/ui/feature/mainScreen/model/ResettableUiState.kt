package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.model

data class ResettableUiState(
    val isLoading: Boolean = false,
    val isDone: Boolean = false,
    val error: String? = null
)
