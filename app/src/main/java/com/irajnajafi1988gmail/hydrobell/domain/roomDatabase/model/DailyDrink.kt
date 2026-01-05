package com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model

data class DailyDrink(
    val date: String,
    val totalDrink: Int,
    val isCompleted: Boolean = false
)
