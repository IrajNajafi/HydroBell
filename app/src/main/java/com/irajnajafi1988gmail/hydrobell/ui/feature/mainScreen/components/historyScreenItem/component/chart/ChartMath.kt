package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.component.chart

import androidx.compose.ui.unit.Dp

object ChartMath {

    fun ratio(value: Int, target: Int): Float =
        (value.toFloat() / (if (target > 0) target else 1))
            .coerceIn(0f, 1.2f)

    fun barHeight(
        ratio: Float,
        chartHeight: Dp,
        factor: Float
    ): Dp = chartHeight * factor * ratio
}
