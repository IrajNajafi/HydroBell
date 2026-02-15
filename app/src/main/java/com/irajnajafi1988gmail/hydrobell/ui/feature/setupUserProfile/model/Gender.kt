package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model

import androidx.annotation.StringRes
import com.irajnajafi1988gmail.hydrobell.R

enum class Gender(
    val code: String,
    @StringRes val label: Int) {
    NONE(code = "none", label = R.string.none),
    MALE(code = "male", label = R.string.male),
    FEMALE(code = "female", label = R.string.female);


}