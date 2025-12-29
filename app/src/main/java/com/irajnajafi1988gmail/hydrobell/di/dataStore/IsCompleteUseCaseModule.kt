package com.irajnajafi1988gmail.hydrobell.di.dataStore

import com.irajnajafi1988gmail.hydrobell.domain.datastore.model.IsCompleteUseCase
import com.irajnajafi1988gmail.hydrobell.domain.datastore.repository.IsCompleteRepository
import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.GetIsProfileCompleteUseCase
import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.ResetProfileCompleteUseCase
import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.SetProfileCompleteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object IsCompleteUseCaseModule {

    @Provides
    @Singleton
    fun providerIcCompleteUseCase(
        repository: IsCompleteRepository
    ): IsCompleteUseCase{
        return IsCompleteUseCase(
            getIsProfileCompleteUseCase = GetIsProfileCompleteUseCase(repository),
            setProfileCompleteUseCase = SetProfileCompleteUseCase(repository),
            resetProfileCompleteUseCase = ResetProfileCompleteUseCase(repository),


        )
    }
}