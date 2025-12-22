package com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase

import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.UserProfile
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.repository.UserProfileRepository
import javax.inject.Inject

class InsertUserProfileUseCase @Inject constructor(
    private val repository: UserProfileRepository
) {
    suspend operator fun invoke(profile: UserProfile) = repository.insertUserProfile(profile)
}

class GetUserProfileUseCase @Inject constructor(
    private val repository: UserProfileRepository
) {
    suspend operator fun invoke() = repository.getUserProfile()
}

class ClearUserProfileUseCase @Inject constructor(
    private val repository: UserProfileRepository
) {
    suspend operator fun invoke() = repository.clearUserProfile()
}