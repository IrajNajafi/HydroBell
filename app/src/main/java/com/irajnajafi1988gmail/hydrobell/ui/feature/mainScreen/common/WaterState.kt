package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.common

import androidx.compose.ui.graphics.Color
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.model.WaterState
import com.irajnajafi1988gmail.hydrobell.ui.theme.BluePrimary
import com.irajnajafi1988gmail.hydrobell.ui.theme.LevelHighBorder
import com.irajnajafi1988gmail.hydrobell.ui.theme.LevelMediumBorder

fun WaterState.toColor(): Color = when (this) {
    WaterState.START  -> BluePrimary
    WaterState.NORMAL -> BluePrimary
    WaterState.GOAL   -> LevelMediumBorder
    WaterState.OVER   -> LevelHighBorder
}

