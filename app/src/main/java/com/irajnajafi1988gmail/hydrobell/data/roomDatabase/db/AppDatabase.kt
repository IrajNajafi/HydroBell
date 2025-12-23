package com.irajnajafi1988gmail.hydrobell.data.roomDatabase.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.converters.UserProfileConverters
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.dao.DailyDrinkDao
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.dao.UserProfileDao
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.entities.UserProfileEntity

@Database(
    entities = [
        UserProfileEntity::class
    ],
    version = AppDatabase.VERSION,
    exportSchema = false
)
@TypeConverters(
    UserProfileConverters::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userProfileDao(): UserProfileDao
    abstract fun dailyDrinkDao(): DailyDrinkDao

    companion object {
        const val VERSION = 1
        const val DBNAME = "hydroBell_db"
        const val USERPROFILE_TABLE = "userprofile"

        const val DAILY_DRINK_TABLE = "daily_drink"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DBNAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
