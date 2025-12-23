package com.irajnajafi1988gmail.hydrobell.di.roomDatabase

import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.DailyDrinkUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.repository.DailyDrinkRepository
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.GetAllHistoryUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.GetDrinkByDateUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.UpsertDailyDrinkUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DailyDrinkUseCaseModule {
    @Provides
    @Singleton
    fun provideDailyDrinkUseCase(
        repository: DailyDrinkRepository
    ): DailyDrinkUseCase {
        return DailyDrinkUseCase(
            upsertDailyDrinkUseCase = UpsertDailyDrinkUseCase(repository),
            getDrinkByDateUseCase = GetDrinkByDateUseCase(repository),
            getAllHistoryUseCase = GetAllHistoryUseCase(repository)

        )
    }
}