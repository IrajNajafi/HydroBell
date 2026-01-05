package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.component.chart

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.model.ChartBarData

@Composable
fun ChartBody(
    data: List<ChartBarData>,
    selectedItem: ChartBarData?,
    onItemSelected: (ChartBarData) -> Unit,
    scrollState: ScrollState,
    showCompletionMarks: Boolean = false
) {
    val yAxisWidth = 50.dp
    val labelHeight = 40.dp
    val chartHeight = 220.dp
    val barMaxHeightFactor = 0.8f

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(chartHeight + labelHeight)
    ) {

        ChartGrid(
            modifier = Modifier.fillMaxSize(),
            yAxisWidth = yAxisWidth,
            chartHeight = chartHeight,
            barMaxHeightFactor = barMaxHeightFactor
        )

        YAxisLabels(
            modifier = Modifier
                .height(chartHeight)
                .width(yAxisWidth),
            chartHeight = chartHeight,
            factor = barMaxHeightFactor
        )

        ChartBarsRow(
            data = data,
            selectedItem = selectedItem,
            onItemSelected = onItemSelected,
            scrollState = scrollState,
            chartHeight = chartHeight,
            yAxisWidth = yAxisWidth,
            labelHeight = labelHeight,
            barMaxHeightFactor = barMaxHeightFactor,
            showCompletionMarks = showCompletionMarks
        )
    }
}
