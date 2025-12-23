package com.irajnajafi1988gmail.hydrobell.di.roomDatabase

import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.repository.UserProfileRepositoryImpl
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.repository.UserProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UserProfileRepositoryModule {
    @Binds
    abstract fun bindUserProfileRepository(
        impl: UserProfileRepositoryImpl
    ): UserProfileRepository
}