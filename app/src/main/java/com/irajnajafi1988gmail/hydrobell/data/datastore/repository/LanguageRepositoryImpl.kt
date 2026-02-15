package com.irajnajafi1988gmail.hydrobell.data.datastore.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.irajnajafi1988gmail.hydrobell.data.datastore.prefkeys.LanguagePrefKeys
import com.irajnajafi1988gmail.hydrobell.data.datastore.provider.LanguageDataStore
import com.irajnajafi1988gmail.hydrobell.domain.datastore.repository.LanguageRepository
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.model.ItemLanguage
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageRepositoryImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
) : LanguageRepository {
    override fun getLanguageState(): Flow<ItemLanguage> =
        context.LanguageDataStore.data
            .map { preferences ->
                val code = preferences[LanguagePrefKeys.LANGUAGE_CODE] ?: ItemLanguage.ENGLISH.code
                ItemLanguage.fromCode(code)
            }


    override suspend fun saveLanguageState(language: ItemLanguage) {
        context.LanguageDataStore.edit { preferences ->
            preferences[LanguagePrefKeys.LANGUAGE_CODE] = language.code
        }
    }
}