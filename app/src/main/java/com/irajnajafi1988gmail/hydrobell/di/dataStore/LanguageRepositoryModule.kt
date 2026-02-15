package com.irajnajafi1988gmail.hydrobell.di.dataStore

import com.irajnajafi1988gmail.hydrobell.data.datastore.repository.LanguageRepositoryImpl
import com.irajnajafi1988gmail.hydrobell.domain.datastore.repository.LanguageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LanguageRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindLanguageRepository(
        impl: LanguageRepositoryImpl
    ): LanguageRepository
}