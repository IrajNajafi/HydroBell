package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.model



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


