package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.model.HistoryRange
import com.irajnajafi1988gmail.hydrobell.ui.theme.SkyBlue

@Composable
fun HistoryRangeSelector(
    selectedRange: HistoryRange,
    onRangeChange: (HistoryRange) -> Unit
) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(35.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = SkyBlue)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HistoryRange.entries.forEach { range ->
                val isSelected = range == selectedRange

                val backgroundColor by animateColorAsState(
                    targetValue = if (isSelected) Color.White else SkyBlue,
                    animationSpec = tween(300),
                    label = ""
                )
                val textColor by animateColorAsState(
                    targetValue = if (isSelected) Color.Black else Color.White,
                    animationSpec = tween(300),
                    label = ""
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(8.dp))
                        .background(backgroundColor)
                        .clickable { onRangeChange(range) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = range.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )
                }
            }
        }
    }
}
