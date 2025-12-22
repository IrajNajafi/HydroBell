package com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.repository

import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.UserProfile

interface UserProfileRepository {

    suspend fun insertUserProfile(profile: UserProfile)

    suspend fun getUserProfile(): UserProfile?

    suspend fun clearUserProfile()
}
