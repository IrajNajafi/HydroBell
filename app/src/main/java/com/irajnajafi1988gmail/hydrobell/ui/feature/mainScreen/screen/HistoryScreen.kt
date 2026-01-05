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
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.component.WeeklyCompletion
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.component.chart.BarChartPercent
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.model.HistoryRange
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.viewModel.HistoryViewModel
@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel()
) {

    val title by viewModel.title.collectAsState()
    val chartData by viewModel.chartData.collectAsState()
    val dailyTarget by viewModel.dailyTarget.collectAsState()
    val selectedRange by viewModel.range.collectAsState()
    val canGoPrevious by viewModel.canGoPrevious.collectAsState()
    val canGoNext by viewModel.canGoNext.collectAsState()
    val referenceDate by viewModel.referenceDate.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(R.drawable.arrow_back),
                contentDescription = "Previous",
                tint = if (canGoPrevious)
                    androidx.compose.material3.MaterialTheme.colorScheme.onBackground
                else Color.LightGray,
                modifier = Modifier
                    .size(24.dp)
                    .then(
                        if (canGoPrevious)
                            Modifier.clickable { viewModel.movePrevious() }
                        else Modifier
                    )
            )

            Spacer(Modifier.width(8.dp))

            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.width(8.dp))

            Icon(
                painter = painterResource(R.drawable.arrow_back),
                contentDescription = "Next",
                tint = if (canGoNext)
                    androidx.compose.material3.MaterialTheme.colorScheme.onBackground
                else Color.LightGray,
                modifier = Modifier
                    .size(24.dp)
                    .rotate(180f)
                    .then(
                        if (canGoNext)
                            Modifier.clickable { viewModel.moveNext() }
                        else Modifier
                    )
            )
        }

        Spacer(Modifier.height(12.dp))

        BarChartPercent(
            data = chartData,
            dailyTarget = dailyTarget,
            showCompletionMarks = selectedRange != HistoryRange.WEEK
        )

        Spacer(Modifier.height(20.dp))

        HistoryRangeSelector(
            selectedRange = selectedRange,
            onRangeChange = viewModel::changeRange
        )

        if (selectedRange == HistoryRange.WEEK) {
            Spacer(Modifier.height(20.dp))
            WeeklyCompletion(
                weekData = chartData,
                anchorDate = referenceDate
            )
        }
    }
}
