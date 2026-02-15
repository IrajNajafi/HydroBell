package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model

import androidx.annotation.StringRes
import com.irajnajafi1988gmail.hydrobell.R

enum class Environment(
    val code: String,
    @StringRes val label: Int
) {
    NONE(code = "none", label = R.string.none),
    FREEZING(code = "freezing", label = R.string.freezing),
    COLD(code = "cold", label = R.string.cold),
    NORMAL(code = "normal", label = R.string.normal),
    WARM(code = "warm", label = R.string.warm),
    HOT(code = "hot", label = R.string.hot)
}