package com.irajnajafi1988gmail.hydrobell.data.roomDatabase

import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.dao.UserProfileDao
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.mapper.toDomain
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.mapper.toEntity
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.UserProfile
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.repository.UserProfileRepository
import javax.inject.Inject

class UserProfileRepositoryImpl @Inject constructor(
    private val userProfileDao: UserProfileDao
): UserProfileRepository {
    override suspend fun insertUserProfile(profile: UserProfile) {
        userProfileDao.insertUserProfile(profile.toEntity())
    }

    override suspend fun getUserProfile(): UserProfile? {
       return userProfileDao.getUserProfile()?.toDomain()
    }

    override suspend fun clearUserProfile() {
        userProfileDao.clearUserProfile()
    }
}