package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.component.chart

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ChartGrid(
    modifier: Modifier,
    yAxisWidth: Dp,
    chartHeight: Dp,
    barMaxHeightFactor: Float
) {
    Canvas(modifier) {
        val yAxisX = yAxisWidth.toPx()
        val bottom = chartHeight.toPx()

        listOf( 0.5f, 1f).forEach { fraction ->
            val y = bottom - (fraction * bottom * barMaxHeightFactor)
            drawLine(
                color = Color.DarkGray.copy(alpha = 0.3f),
                start = Offset(yAxisX, y),
                end = Offset(size.width, y),
                strokeWidth = 1.dp.toPx(),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
            )
        }

        drawLine(
            color = Color.Black.copy(alpha = 0.4f),
            start = Offset(yAxisX, 0f),
            end = Offset(yAxisX, bottom),
            strokeWidth = 2.dp.toPx()
        )

        drawLine(
            color = Color.Black.copy(alpha = 0.4f),
            start = Offset(yAxisX, bottom),
            end = Offset(size.width, bottom),
            strokeWidth = 2.dp.toPx()
        )
    }
}
