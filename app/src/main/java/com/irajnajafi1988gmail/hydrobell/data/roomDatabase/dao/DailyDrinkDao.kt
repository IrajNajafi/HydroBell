package com.irajnajafi1988gmail.hydrobell.data.roomDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.db.AppDatabase
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.entities.DailyDrinkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyDrinkDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(daily: DailyDrinkEntity)


    @Query("SELECT * FROM ${AppDatabase.DAILY_DRINK_TABLE} WHERE date = :date LIMIT 1")
    fun getByDate(date: String): Flow<DailyDrinkEntity?>


    @Query("SELECT * FROM ${AppDatabase.DAILY_DRINK_TABLE} ORDER BY date DESC")
    fun getAll(): Flow<List<DailyDrinkEntity>>

    @Query("SELECT * FROM ${AppDatabase.DAILY_DRINK_TABLE} ORDER BY date DESC LIMIT 1")
    fun getLatest(): Flow<DailyDrinkEntity?>

    @Query("DELETE FROM ${AppDatabase.DAILY_DRINK_TABLE}")
    suspend fun clearAll()
}
