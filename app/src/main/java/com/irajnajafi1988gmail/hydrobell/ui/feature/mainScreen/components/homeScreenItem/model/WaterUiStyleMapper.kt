package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.model

import androidx.compose.ui.graphics.Color

fun WaterState.toUiStyle(): WaterUiStyle = when (this) {

    WaterState.START -> WaterUiStyle(
        progressColor = Color(0xFF00A5FF),
        centerBg = Color.White,
        gradient = listOf(Color.White, Color.LightGray)
    )

    WaterState.NORMAL -> WaterUiStyle(
        progressColor = Color(0xFF00A5FF),
        centerBg = Color(0xFFD6E7FD),
        gradient = listOf(Color(0xFFABCFF6), Color(0xFFD6E7FD))
    )

    WaterState.GOAL -> WaterUiStyle(
        progressColor = Color(0xFF4CAF50),
        centerBg = Color(0xFFE8F5E9),
        gradient = listOf(Color(0xFFB2DFDB), Color(0xFFE8F5E9))
    )

    WaterState.OVER -> WaterUiStyle(
        progressColor = Color(0xFFDCE775),
        centerBg = Color(0xFFFFFDE7),
        gradient = listOf(Color(0xFFF0F4C3), Color(0xFFFFFDE7))
    )
}