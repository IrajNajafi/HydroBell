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
import androidx.compose.ui.unit.dp

@Composable
fun StepProgressLine(
    modifier: Modifier = Modifier,
    color: Color = Color.Blue,
    progress: Float // 0f تا 1f
) {
    // Animate progress smoothly when it changes
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = androidx.compose.animation.core.tween(
            durationMillis = 500
        )
    )

    Canvas(modifier = modifier.height(6.dp)) {
        val lineWidth = size.width * animatedProgress
        drawLine(
            color = color,
            start = Offset(0f, size.height / 2),
            end = Offset(lineWidth, size.height / 2),
            strokeWidth = size.height,
            cap = StrokeCap.Round
        )
    }
}
