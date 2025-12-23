package com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.repository

import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.DailyDrink

interface DailyDrinkRepository {
    suspend fun upsertDailyDrink(daily: DailyDrink)
    suspend fun getDrinkByDate(date: String): DailyDrink?
    suspend fun getAllHistory(): List<DailyDrink>
}