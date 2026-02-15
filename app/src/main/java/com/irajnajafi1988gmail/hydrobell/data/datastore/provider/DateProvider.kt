package com.irajnajafi1988gmail.hydrobell.data.datastore.provider

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.irajnajafi1988gmail.hydrobell.data.datastore.prefkeys.DatePreferencesKeys

val Context.dateDataStore by preferencesDataStore(name = DatePreferencesKeys.CALENDAR_TYPE)