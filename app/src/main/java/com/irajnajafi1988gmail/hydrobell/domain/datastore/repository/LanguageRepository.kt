package com.irajnajafi1988gmail.hydrobell.domain.datastore.repository

import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.model.ItemLanguage
import kotlinx.coroutines.flow.Flow

interface LanguageRepository {

    fun getLanguageState(): Flow<ItemLanguage>
    suspend fun saveLanguageState(language: ItemLanguage)
}