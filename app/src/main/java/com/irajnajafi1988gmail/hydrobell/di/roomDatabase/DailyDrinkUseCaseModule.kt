package com.irajnajafi1988gmail.hydrobell.di.roomDatabase

import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.DailyDrinkUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.repository.DailyDrinkRepository
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.AddAmountToDailyDrinkUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.ClearAllUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.GetAllUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.GetByDateUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.GetLatestUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.ResetTodayDrinkUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.SetDayCompletedUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.UpsertUseCase
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
            upsert = UpsertUseCase(repository),
            getByDate = GetByDateUseCase(repository),
            getAll = GetAllUseCase(repository),
            getLatest = GetLatestUseCase(repository),
            clearAll = ClearAllUseCase(repository),
            addAmountToDailyDrinkUseCase = AddAmountToDailyDrinkUseCase(repository),
            resetTodayDrinkUseCase = ResetTodayDrinkUseCase(repository),
            setDayCompletedUseCase = SetDayCompletedUseCase(repository)

        )
    }
}