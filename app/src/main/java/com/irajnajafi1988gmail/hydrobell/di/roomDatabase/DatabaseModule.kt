package com.irajnajafi1988gmail.hydrobell.di.roomDatabase

import android.content.Context
import androidx.room.Room
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.dao.DailyDrinkDao
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.dao.UserProfileDao
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DBNAME
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }


    @Provides
    fun providerUserProfileDao(db: AppDatabase): UserProfileDao = db.userProfileDao()

    @Provides
    fun providerDailyDrinkDao(db: AppDatabase): DailyDrinkDao = db.dailyDrinkDao()
}
