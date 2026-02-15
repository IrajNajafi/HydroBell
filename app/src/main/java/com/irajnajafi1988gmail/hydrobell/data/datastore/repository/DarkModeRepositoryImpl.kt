package com.irajnajafi1988gmail.hydrobell.data.datastore.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.irajnajafi1988gmail.hydrobell.data.datastore.prefkeys.DarkModePrefKeys
import com.irajnajafi1988gmail.hydrobell.data.datastore.provider.DarkModeDataStore
import com.irajnajafi1988gmail.hydrobell.domain.datastore.repository.DarkModeRepository
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.model.ItemDarkMode
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DarkModeRepositoryImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
) : DarkModeRepository {
    override fun getDarkModeState(): Flow<ItemDarkMode> =
        context.DarkModeDataStore.data
            .map { preferences ->
                val code = preferences[DarkModePrefKeys.DARK_MODE_CODE] ?: ItemDarkMode.SYSTEM.code
                ItemDarkMode.fromCode(code)
            }


    override suspend fun saveDarkModeState(mode: ItemDarkMode) {
        context.DarkModeDataStore.edit { prefs ->
            prefs[DarkModePrefKeys.DARK_MODE_CODE] = mode.code
        }
    }
}