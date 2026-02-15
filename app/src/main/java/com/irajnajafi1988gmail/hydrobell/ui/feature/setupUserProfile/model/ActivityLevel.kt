package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model

import androidx.annotation.StringRes
import com.irajnajafi1988gmail.hydrobell.R

enum class ActivityLevel(
    val code: String,
    @StringRes val label: Int
) {
    NONE(code = "none", label = R.string.none),
    LOW(code = "low",label = R.string.low),
    MEDIUM(code = "medium", label = R.string.medium),
    HIGH(code = "high", label = R.string.high)
}