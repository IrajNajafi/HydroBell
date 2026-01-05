package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.component.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.model.ChartBarData
import com.irajnajafi1988gmail.hydrobell.ui.theme.BluePrimary

@Composable
fun ChartBarItem(
    item: ChartBarData,
    isSelected: Boolean,
    chartHeight: Dp,
    barHeight: Dp,
    labelHeight: Dp,
    onClick: () -> Unit,
    showCompletionMarks: Boolean = false // ✅ فعلاً پارامتر موجوده
) {
    val valueAreaHeight = 43.dp

    Column(
        modifier = Modifier
            .width(52.dp)
            .height(chartHeight + labelHeight)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔹 ناحیه متن بالا
        Box(
            modifier = Modifier.height(valueAreaHeight),
            contentAlignment = Alignment.BottomCenter
        ) {
            if (item.value > 0 || showCompletionMarks) { // ✅ شرط اصلاح شد
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = if (showCompletionMarks) {
                            // نمایش درصد تکمیل وقتی حالت ماه/سال است
                            "${(item.value * 100 / item.target)}%"
                        } else {
                            // نمایش مقدار واقعی وقتی حالت هفته است
                            "${item.value}"
                        },
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) BluePrimary else Color.DarkGray
                    )
                    if (!showCompletionMarks) {
                        Text(
                            text = "ml",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }

        // 🔹 ناحیه میله (باقی‌مانده ارتفاع)
        Box(
            modifier = Modifier
                .height(chartHeight - valueAreaHeight),
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .width(28.dp)
                    .height(barHeight)
                    .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                    .background(
                        if (isSelected) BluePrimary
                        else getBarColor(item.value, item.target)
                    )
            )
        }

        // 🔹 لیبل پایین
        Box(
            modifier = Modifier.height(labelHeight),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = item.label,
                fontSize = 13.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) Color.Black else Color.Gray
            )
        }
    }
}
