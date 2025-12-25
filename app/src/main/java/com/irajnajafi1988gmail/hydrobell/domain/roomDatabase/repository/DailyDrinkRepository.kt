package com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.repository

import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.DailyDrink
import kotlinx.coroutines.flow.Flow

interface DailyDrinkRepository {
    suspend fun upsert(daily: DailyDrink)
   fun getByDate(date: String): Flow<DailyDrink?>

    fun getAll(): Flow<List<DailyDrink>>

    fun getLatest(): Flow<DailyDrink?>

    suspend fun clearAll()

}