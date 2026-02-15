package com.irajnajafi1988gmail.hydrobell.di.dataStore

import com.irajnajafi1988gmail.hydrobell.data.datastore.repository.DateRepositoryImpl
import com.irajnajafi1988gmail.hydrobell.domain.datastore.repository.DateRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DateModule {
    @Binds
    @Singleton
    abstract fun bindDateRepository(
        impl: DateRepositoryImpl
    ): DateRepository
}