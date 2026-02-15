package com.irajnajafi1988gmail.hydrobell.data.datastore.provider

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.irajnajafi1988gmail.hydrobell.data.datastore.prefkeys.DarkModePrefKeys

val Context.DarkModeDataStore by preferencesDataStore(name = DarkModePrefKeys.DARK_MODE_NAME)