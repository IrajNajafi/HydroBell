package com.irajnajafi1988gmail.hydrobell.di.dataStore

import com.irajnajafi1988gmail.hydrobell.domain.datastore.model.LanguageUseCase
import com.irajnajafi1988gmail.hydrobell.domain.datastore.repository.LanguageRepository
import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.GetLanguageStateUseCase
import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.SaveLanguageStateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LanguageUseCaseModule {
    @Provides
    @Singleton
    fun providerLanguageUseCase(
        repository: LanguageRepository
    ): LanguageUseCase {
        return LanguageUseCase(
            getLanguageStateUseCase = GetLanguageStateUseCase(repository),
            saveLanguageStateUseCase = SaveLanguageStateUseCase(repository)
        )
    }
}