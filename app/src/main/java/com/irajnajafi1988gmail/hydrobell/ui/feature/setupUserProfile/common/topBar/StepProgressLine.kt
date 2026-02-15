package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.common.topBar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
fun StepProgressLine(
    modifier: Modifier = Modifier,
    color: Color = Color.Blue,
    progress: Float // 0f تا 1f
) {
    val layoutDirection = LocalLayoutDirection.current

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = androidx.compose.animation.core.tween(
            durationMillis = 500
        ),
        label = ""
    )

    Canvas(modifier = modifier.height(6.dp)) {

        val centerY = size.height / 2
        val lineWidth = size.width * animatedProgress

        val startX: Float
        val endX: Float

        if (layoutDirection == LayoutDirection.Rtl) {
            startX = size.width
            endX = size.width - lineWidth
        } else {
            startX = 0f
            endX = lineWidth
        }

        drawLine(
            color = color,
            start = Offset(startX, centerY),
            end = Offset(endX, centerY),
            strokeWidth = size.height,
            cap = StrokeCap.Round
        )
    }
}
