package com.irajnajafi1988gmail.hydrobell.di.roomDatabase

import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.repository.DailyDrinkRepositoryImpl
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.repository.DailyDrinkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DailyDrinkRepositoryModule {

    @Binds
    abstract fun bindDailyDrinkRepository(
        impl: DailyDrinkRepositoryImpl
    ): DailyDrinkRepository
}