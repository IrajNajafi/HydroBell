package com.irajnajafi1988gmail.hydrobell.data.datastore.provider

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.irajnajafi1988gmail.hydrobell.data.datastore.prefkeys.DishesPrefKeys

val Context.DishesPreferencesDataStore by preferencesDataStore(name = DishesPrefKeys.DISHES_NAME )