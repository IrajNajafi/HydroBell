package com.irajnajafi1988gmail.hydrobell.di.dataStore

import com.irajnajafi1988gmail.hydrobell.data.datastore.repository.DishesRepositoryImpl
import com.irajnajafi1988gmail.hydrobell.domain.datastore.repository.DishesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.xml.sax.helpers.LocatorImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DishesRepositoryModule {
    @Binds
    @Singleton
    fun bindDishesRepository(
        impl: DishesRepositoryImpl
    ): DishesRepository
}