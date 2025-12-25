package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.model

import androidx.annotation.DrawableRes
import com.irajnajafi1988gmail.hydrobell.R

data class ItemDishes(
    @DrawableRes val icon: Int = R.drawable.cup175, // همیشه یک drawable معتبر
    val label: String = "175 ml", // مقدار پیش‌فرض امن
    val volumeMl: Int = 175 // مقدار پیش‌فرض امن
)
