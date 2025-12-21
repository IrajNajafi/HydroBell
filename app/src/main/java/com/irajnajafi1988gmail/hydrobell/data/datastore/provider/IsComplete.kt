package com.irajnajafi1988gmail.hydrobell.data.datastore.provider

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.irajnajafi1988gmail.hydrobell.data.datastore.prefkeys.IsCompletePrefKeys

val Context.IsCompleteDataStore by preferencesDataStore(name = IsCompletePrefKeys.IS_COMPLETE_NAME)