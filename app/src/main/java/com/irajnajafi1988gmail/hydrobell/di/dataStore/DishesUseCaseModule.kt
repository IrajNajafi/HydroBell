package com.irajnajafi1988gmail.hydrobell.di.dataStore

import com.irajnajafi1988gmail.hydrobell.domain.datastore.model.DishesUseCase
import com.irajnajafi1988gmail.hydrobell.domain.datastore.repository.DishesRepository
import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.GetSelectedDishUseCase
import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.RestartDishesUseCase
import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.SaveDishesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DishesUseCaseModule {
    @Provides
    @Singleton
    fun providerDishesUseCase(
        repository: DishesRepository
    ): DishesUseCase{
        return DishesUseCase(
            getSelectedDishUseCase = GetSelectedDishUseCase(repository),
            saveDishesUseCase = SaveDishesUseCase(repository),
            restartDishesUseCase = RestartDishesUseCase(repository)
        )
    }
}