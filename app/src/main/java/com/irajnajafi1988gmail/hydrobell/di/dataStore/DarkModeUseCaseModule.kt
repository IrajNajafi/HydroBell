package com.irajnajafi1988gmail.hydrobell.di.dataStore

import com.irajnajafi1988gmail.hydrobell.domain.datastore.model.DarkModeUseCase
import com.irajnajafi1988gmail.hydrobell.domain.datastore.repository.DarkModeRepository
import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.GetDarkModeStateUseCase
import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.SaveDarkModeStateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DarkModeUseCaseModule {
    @Provides
    @Singleton
    fun providerDarkModeUseCase(
        repository: DarkModeRepository
    ): DarkModeUseCase {
        return DarkModeUseCase(
            getDarkModeStateUseCase = GetDarkModeStateUseCase(repository),
            saveDarkModeStateUseCase = SaveDarkModeStateUseCase(repository)
        )

    }
}