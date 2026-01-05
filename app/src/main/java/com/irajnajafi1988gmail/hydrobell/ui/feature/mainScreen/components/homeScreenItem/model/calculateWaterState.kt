package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.model

import com.irajnajafi1988gmail.hydrobell.ui.theme.BluePrimary
import com.irajnajafi1988gmail.hydrobell.ui.theme.LevelHighBorder
import com.irajnajafi1988gmail.hydrobell.ui.theme.LevelMediumBorder


fun calculateWaterState(
    drunkWater: Int,
    dailyWaterMl: Int
): WaterState {

    if (dailyWaterMl <= 0 || drunkWater <= 0) return WaterState.START

    val percent = drunkWater.toFloat() / dailyWaterMl.toFloat()

    return when {
        percent < 0.95f -> WaterState.NORMAL
        percent <= 1.05f -> WaterState.GOAL
        else -> WaterState.OVER
    }
}


