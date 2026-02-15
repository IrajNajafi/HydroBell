package com.irajnajafi1988gmail.hydrobell.data.datastore.provider

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.irajnajafi1988gmail.hydrobell.data.datastore.prefkeys.LanguagePrefKeys

val Context.LanguageDataStore by preferencesDataStore(name = LanguagePrefKeys.LANGUAGE_NAME)