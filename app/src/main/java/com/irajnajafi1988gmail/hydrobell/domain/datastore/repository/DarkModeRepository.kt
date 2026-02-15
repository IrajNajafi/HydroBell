package com.irajnajafi1988gmail.hydrobell.domain.datastore.repository

import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.model.ItemDarkMode
import kotlinx.coroutines.flow.Flow

interface DarkModeRepository {
    fun getDarkModeState(): Flow<ItemDarkMode>
    suspend fun saveDarkModeState(mode: ItemDarkMode)
}