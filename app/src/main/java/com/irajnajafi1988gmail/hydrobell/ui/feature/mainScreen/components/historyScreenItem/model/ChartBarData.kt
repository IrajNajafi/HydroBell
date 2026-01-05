package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.model

import java.time.LocalDate

data class ChartBarData(
    val date: LocalDate,
    val label: String,   // Sat, Sun, ...
    val value: Int,
    val target: Int,
    val isCompleted: Boolean
)