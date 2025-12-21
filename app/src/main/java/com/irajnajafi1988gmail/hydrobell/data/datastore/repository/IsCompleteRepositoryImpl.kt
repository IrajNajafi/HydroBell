package com.irajnajafi1988gmail.hydrobell.data.datastore.repository

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import com.irajnajafi1988gmail.hydrobell.data.datastore.prefkeys.IsCompletePrefKeys
import com.irajnajafi1988gmail.hydrobell.data.datastore.provider.IsCompleteDataStore
import com.irajnajafi1988gmail.hydrobell.domain.datastore.repository.IsCompleteRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

const val TAG = "IsCompleteRepositoryImpl"

@Singleton
class IsCompleteRepositoryImpl @Inject constructor(
    @ApplicationContext
    private val context: Context

): IsCompleteRepository{
    override fun getIsProfileCompleteFlow(): Flow<Boolean>  = context.IsCompleteDataStore.data.map{preferences ->
        val getComplete = preferences[IsCompletePrefKeys.PROFILE_COMPLETE_KEY]?:false
        getComplete
    }

    override suspend fun setProfileComplete(isComplete: Boolean) {
        context.IsCompleteDataStore.edit { preferences ->
            preferences[IsCompletePrefKeys.PROFILE_COMPLETE_KEY] = isComplete
        }
    }

}
