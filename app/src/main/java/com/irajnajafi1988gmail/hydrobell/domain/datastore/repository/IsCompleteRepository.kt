package com.irajnajafi1988gmail.hydrobell.domain.datastore.repository

import kotlinx.coroutines.flow.Flow

interface IsCompleteRepository {
    fun getIsProfileCompleteFlow(): Flow<Boolean>
    suspend fun setProfileComplete(isComplete: Boolean)
}