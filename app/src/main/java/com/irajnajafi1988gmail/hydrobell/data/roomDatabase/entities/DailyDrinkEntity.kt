package com.irajnajafi1988gmail.hydrobell.data.roomDatabase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.db.AppDatabase

@Entity(tableName = AppDatabase.DAILY_DRINK_TABLE)
data class DailyDrinkEntity(
    @PrimaryKey
    val date: String,
    val totalDrink: Int,
    val isCompleted: Boolean
    )
