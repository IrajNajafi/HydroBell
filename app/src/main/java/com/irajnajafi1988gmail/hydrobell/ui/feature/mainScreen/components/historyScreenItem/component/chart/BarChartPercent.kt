package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.component.chart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.model.ChartBarData
@Composable
fun BarChartPercent(
    data: List<ChartBarData>,
    dailyTarget: Int
) {
    val scrollState = rememberScrollState()
    var selectedItem by remember { mutableStateOf<ChartBarData?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        ChartHeader(selectedItem)

        Spacer(Modifier.height(10.dp))

        ChartBody(
            data = data,
            selectedItem = selectedItem,
            onItemSelected = { selectedItem = it },
            scrollState = scrollState
        )
    }
}


