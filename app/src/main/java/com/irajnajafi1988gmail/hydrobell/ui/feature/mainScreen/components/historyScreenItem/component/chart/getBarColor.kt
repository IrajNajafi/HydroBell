package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.component.chart

import androidx.compose.ui.graphics.Color
import com.irajnajafi1988gmail.hydrobell.ui.theme.BluePrimary
import com.irajnajafi1988gmail.hydrobell.ui.theme.LevelHighBorder
import com.irajnajafi1988gmail.hydrobell.ui.theme.LevelMediumBorder

fun getBarColor(drunk: Int, goal: Int): Color =
    when {
        goal == 0 || drunk == 0 -> BluePrimary
        drunk < goal -> BluePrimary
        drunk == goal ->LevelMediumBorder
        drunk <= (goal * 1.1).toInt() -> LevelMediumBorder
        else -> LevelHighBorder
    }
