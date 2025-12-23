package com.irajnajafi1988gmail.hydrobell.data.roomDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.db.AppDatabase
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.entities.DailyDrinkEntity
@Dao
interface DailyDrinkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertDailyDrink(daily: DailyDrinkEntity)

    @Query("""
        SELECT * FROM daily_drink 
        WHERE date = :date 
        LIMIT 1
    """)
    suspend fun getDrinkByDate(date: String): DailyDrinkEntity?

    @Query("""
        SELECT * FROM daily_drink 
        ORDER BY date DESC
    """)
    suspend fun getAllHistory(): List<DailyDrinkEntity>
}
