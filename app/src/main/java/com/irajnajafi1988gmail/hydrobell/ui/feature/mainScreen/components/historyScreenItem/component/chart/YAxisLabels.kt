package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.component.chart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun YAxisLabels(
    modifier: Modifier,
    chartHeight: Dp,
    factor: Float
) {
    Box(modifier) {
        listOf("0%" to 0f, "50%" to 0.5f, "100%" to 1f).forEach { (label, fraction) ->
            Text(
                text = label,
                fontSize = 10.sp,
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 8.dp)
                    .offset(y = -(chartHeight.value * fraction * factor).dp + 8.dp)
            )
        }
    }
}
