package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.common.topBar

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable

@Composable
fun arrowShape() = GenericShape { size, _ ->
    moveTo(0f, 0f)
    lineTo(size.width * 0.8f, 0f)
    lineTo(size.width, size.height / 2)
    lineTo(size.width * 0.8f, size.height)
    lineTo(0f, size.height)
    close()
}