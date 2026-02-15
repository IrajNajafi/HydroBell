package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.model

data class ChartBarUI(
    val timestamp: Long,
    val value: Int,
    val target: Int,
    val isCompleted: Boolean,
    val label: String
)
