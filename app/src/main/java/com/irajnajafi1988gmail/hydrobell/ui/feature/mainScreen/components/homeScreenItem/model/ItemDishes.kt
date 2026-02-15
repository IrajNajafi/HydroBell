package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.model

import androidx.annotation.DrawableRes
import com.irajnajafi1988gmail.hydrobell.R

data class ItemDishes(
    @DrawableRes val icon: Int = R.drawable.cup175,
    val label: String = "175 ml",
    val volumeMl: Int = 175
)