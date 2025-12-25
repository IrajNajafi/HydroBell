package com.irajnajafi1988gmail.hydrobell.data.datastore.prefkeys

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object DishesPrefKeys {
    const val DISHES_NAME = "dishes_name"
    val SELECTED_DISH_ICON_KEY = intPreferencesKey("selected_dish_icon_key")
    val SELECTED_DISH_VOLUME_KEY = intPreferencesKey("selected_dish_volume_key")
    val SELECTED_DISH_LABEL_KEY = stringPreferencesKey("selected_dish_label_key")
}