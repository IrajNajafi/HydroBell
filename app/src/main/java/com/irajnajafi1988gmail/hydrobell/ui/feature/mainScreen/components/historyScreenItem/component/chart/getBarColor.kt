package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.component.chart

import androidx.compose.ui.graphics.Color
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.common.toColor
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.model.calculateWaterState


fun getBarColor(drunk: Int, goal: Int): Color {
    val state = calculateWaterState(
        drunkWater = drunk,
        dailyWaterMl = goal
    )

    return state.toColor()
}
