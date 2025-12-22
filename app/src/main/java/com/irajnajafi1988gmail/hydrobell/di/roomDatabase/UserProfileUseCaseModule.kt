package com.irajnajafi1988gmail.hydrobell.di.roomDatabase

import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.UserProfileUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.repository.UserProfileRepository
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.ClearUserProfileUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.GetUserProfileUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.InsertUserProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserProfileUseCaseModule {

    @Provides
    @Singleton
    fun provideUserProfileUseCase(
        repository: UserProfileRepository
    ): UserProfileUseCase {
        return UserProfileUseCase(
            insertUserProfileUseCase = InsertUserProfileUseCase(repository),
            getUserProfileUseCase = GetUserProfileUseCase(repository),
            clearUserProfileUseCase = ClearUserProfileUseCase(repository)
        )

    }
}