package com.irajnajafi1988gmail.hydrobell.di.dataStore

import com.irajnajafi1988gmail.hydrobell.data.datastore.repository.IsCompleteRepositoryImpl
import com.irajnajafi1988gmail.hydrobell.domain.datastore.repository.IsCompleteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface IsCompleteModule {
    @Binds
    @Singleton
    fun bindIsCompleteRepository(
        impl: IsCompleteRepositoryImpl
    ): IsCompleteRepository
}