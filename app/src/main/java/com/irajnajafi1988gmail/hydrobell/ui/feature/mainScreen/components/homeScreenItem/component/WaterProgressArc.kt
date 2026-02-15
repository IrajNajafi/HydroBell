package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.min

@Composable
fun WaterProgressArc(
    progress: Float,
    color: Color,
    size: Dp,
    backgroundColor: Color = Color.LightGray,
    strokeWidth: Dp = 12.dp
) {
    val safeProgress = min(progress.coerceAtLeast(0f), 1f)

    Canvas(modifier = Modifier.size(size)) {

        val strokePx = strokeWidth.toPx()


        drawArc(
            color = backgroundColor,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            style = Stroke(strokePx)
        )


        drawArc(
            color = color,
            startAngle = 180f,
            sweepAngle = 180f * safeProgress,
            useCenter = false,
            style = Stroke(strokePx)
        )
    }
}
