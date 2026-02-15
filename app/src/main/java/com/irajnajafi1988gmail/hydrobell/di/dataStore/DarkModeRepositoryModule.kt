package com.irajnajafi1988gmail.hydrobell.di.dataStore

import com.irajnajafi1988gmail.hydrobell.data.datastore.repository.DarkModeRepositoryImpl
import com.irajnajafi1988gmail.hydrobell.domain.datastore.repository.DarkModeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

abstract class DarkModeRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindDarkModeRepository(
        impl: DarkModeRepositoryImpl
    ): DarkModeRepository
}