package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.irajnajafi1988gmail.hydrobell.R
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.component.HistoryRangeSelector
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.component.chart.BarChartPercent
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.viewModel.HistoryViewModel

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel(),
) {

    val title by viewModel.title.collectAsState()
    val chartData by viewModel.chartData.collectAsState()
    val dailyTarget by viewModel.dailyTarget.collectAsState()
    val selectedRange by viewModel.range.collectAsState()
    val canGoPrevious by viewModel.canGoPrevious.collectAsState()
    val canGoNext by viewModel.canGoNext.collectAsState()



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,


        ) {

        // ------------------------- Title -------------------------
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(R.drawable.arrow_back),
                contentDescription = null,
                tint = if (canGoPrevious) Color.Black else Color.LightGray,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(enabled = canGoPrevious) { viewModel.movePrevious() }
            )

            Spacer(Modifier.width(8.dp))
            Text(
                text = title,
                color = Color.DarkGray,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.width(8.dp))

            Icon(
                painter = painterResource(R.drawable.arrow_back),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .rotate(180f)
                    .clickable(enabled = canGoNext) { viewModel.moveNext() },
                tint = if (canGoNext) Color.Black else Color.LightGray
            )
        }


        Spacer(Modifier.height(10.dp))

        // ------------------------- Bar Chart -------------------------

        BarChartPercent(
            data = chartData,
            dailyTarget = dailyTarget
        )

        Spacer(Modifier.height(20.dp))

        // ------------------------- Range Selector -------------------------
        HistoryRangeSelector(
            selectedRange = selectedRange,
            onRangeChange = { range ->
                viewModel.changeRange(range)
            }
        )
        Spacer(Modifier.height(20.dp))

    }
}



