package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.irajnajafi1988gmail.hydrobell.R
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.model.calculateWaterState
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.model.toUiStyle

@Composable
fun WaterProgressCard(
    drunkWater: Int,
    dailyWaterMl: Int,
    number: String,
    text: String,
    numberMl: String,
    selectedIcon: Int?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state = calculateWaterState(drunkWater, dailyWaterMl)
    val style = state.toUiStyle()

    val progress = remember(drunkWater, dailyWaterMl) {
        if (dailyWaterMl <= 0) 0f
        else (drunkWater.toFloat() / dailyWaterMl).coerceIn(0f, 1f)
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {

        //half circle
        WaterProgressArc(
            progress = progress,
            color = style.progressColor,
            size = 300.dp
        )
        //drop icon
        WaterSideIcon(
            iconRes = R.drawable.drop,
            modifier = Modifier.offset(x = (-150).dp, y = 25.dp),
            tint = Color(0xFF00A5FF),
            size = 32.dp
        )
        //Adam icon
        WaterSideIcon(
            iconRes = R.drawable.person,
            modifier = Modifier.offset(x = 150.dp, y = 25.dp),
            tint = style.progressColor,
            size = 40.dp
        )

        //big circle
        WaterCenterCircle(
            number = number,
            text = text,
            numberMl = numberMl,
            icon = selectedIcon,
            style = style,
            onClick = onClick
        )
    }
}

@Composable
private fun WaterSideIcon(
    iconRes: Int,
    modifier: Modifier,
    tint: Color,
    size: androidx.compose.ui.unit.Dp
) {
    Icon(
        painter = painterResource(iconRes),
        contentDescription = null,
        modifier = modifier.size(size),
        tint = tint
    )
}
