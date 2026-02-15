package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.screen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.irajnajafi1988gmail.hydrobell.R
import com.irajnajafi1988gmail.hydrobell.navigition.NaveScreen
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.component.HistoryRangeSelector
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.component.WeeklyCompletion
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.component.chart.BarChartPercent
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.model.HistoryRange
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.utils.AppDate
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.viewModel.HistoryViewModel

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel(),
    navController: NavController,
    appDate: AppDate
) {

    BackHandler {
        navController.navigate(NaveScreen.MainScreen.route) {
            popUpTo(NaveScreen.MainScreen.route) { inclusive = true }
        }
    }

    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl

   val  title by viewModel.title.collectAsState()
    val chartData by viewModel.chartData.collectAsState()
    val dailyTarget by viewModel.dailyTarget.collectAsState()
    val selectedRange by viewModel.range.collectAsState()
    val canGoPrevious by viewModel.canGoPrevious.collectAsState()
    val canGoNext by viewModel.canGoNext.collectAsState()
    val referenceDate by viewModel.referenceDate.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            // ---- Previous ----
            Icon(
                painter = painterResource(R.drawable.arrow_back),
                contentDescription = "Previous",
                tint = if (canGoPrevious) MaterialTheme.colorScheme.onBackground else Color.LightGray,
                modifier = Modifier
                    .size(24.dp)
                    .rotate(if (isRtl) 180f else 0f)
                    .then(if (canGoPrevious) Modifier.clickable { viewModel.movePrevious() } else Modifier)
            )

            Spacer(Modifier.width(8.dp))

            Text(
                text = title,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.width(8.dp))

            // ---- Next ----
            Icon(
                painter = painterResource(R.drawable.arrow_back),
                contentDescription = "Next",
                tint = if (canGoNext) MaterialTheme.colorScheme.onBackground else Color.LightGray,
                modifier = Modifier
                    .size(24.dp)
                    .rotate(if (isRtl) 0f else 180f)
                    .then(if (canGoNext) Modifier.clickable { viewModel.moveNext() } else Modifier)
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
                anchorDate = referenceDate,

            )
        }
    }
}
