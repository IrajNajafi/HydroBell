package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model

import androidx.annotation.StringRes


data class ItemGender(
    val image: Int,
    @StringRes val label: Int,
    val gender: Gender
)