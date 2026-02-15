package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.common.topBar

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

data class StepItem(
    val icon: Int,
    @StringRes val label: Int? = null,
    val value: Int? = null,
    val color: Color,
)
