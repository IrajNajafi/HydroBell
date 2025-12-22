package com.irajnajafi1988gmail.hydrobell.data.roomDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.db.AppDatabase
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.entities.UserProfileEntity

@Dao
interface UserProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(profile: UserProfileEntity)

    @Query("SELECT * FROM ${AppDatabase.USERPROFILE_TABLE} LIMIT 1")
    suspend fun getUserProfile(): UserProfileEntity?

    @Query("DELETE FROM ${AppDatabase.USERPROFILE_TABLE}")
    suspend fun clearUserProfile()
}
