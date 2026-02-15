package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.component.chart


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.model.ChartBarData
import com.irajnajafi1988gmail.hydrobell.ui.theme.BluePrimary
import java.time.format.DateTimeFormatter

@Composable
fun ChartHeader(selectedItem: ChartBarData?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = selectedItem != null,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut()
        ) {
            selectedItem?.let {
                Text(
                    text = it.date.format(
                        DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy")
                    ),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = BluePrimary
                )
            }
        }
    }
}