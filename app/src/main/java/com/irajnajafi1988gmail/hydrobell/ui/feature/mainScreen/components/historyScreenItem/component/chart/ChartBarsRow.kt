package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.component.chart

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.model.ChartBarData

@Composable
fun ChartBarsRow(
    data: List<ChartBarData>,
    selectedItem: ChartBarData?,
    onItemSelected: (ChartBarData) -> Unit,
    scrollState: ScrollState,
    chartHeight: Dp,
    yAxisWidth: Dp,
    labelHeight: Dp,
    barMaxHeightFactor: Float,
    showCompletionMarks: Boolean = false
) {
    Row(
        modifier = Modifier
            .padding(start = yAxisWidth)
            .horizontalScroll(scrollState),
        verticalAlignment = Alignment.Top
    ) {
        data.forEach { item ->
            val ratio = ChartMath.ratio(item.value, item.target)
            val barHeight = ChartMath.barHeight(ratio, chartHeight, barMaxHeightFactor)

            ChartBarItem(
                item = item,
                isSelected = item == selectedItem,
                chartHeight = chartHeight,
                barHeight = barHeight,
                labelHeight = labelHeight,
                onClick = { onItemSelected(item) },
                showCompletionMarks = showCompletionMarks // ✅ اصلاح شد
            )
        }
    }
}
