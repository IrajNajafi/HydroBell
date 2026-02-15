package com.irajnajafi1988gmail.hydrobell.di.dataStore

import com.irajnajafi1988gmail.hydrobell.domain.datastore.model.DateUseCase
import com.irajnajafi1988gmail.hydrobell.domain.datastore.repository.DateRepository
import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.GetCalendarTypeUseCase
import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.SaveDateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DateUserCaseModule {
    @Provides
    @Singleton
    fun providerDateUseCase(
        repository: DateRepository
    ): DateUseCase{
        return DateUseCase(
            getCalendarTypeUseCase = GetCalendarTypeUseCase(repository),
             saveDateUseCase =SaveDateUseCase(repository)
        )
    }
}