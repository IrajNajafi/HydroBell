package com.irajnajafi1988gmail.hydrobell.data.datastore.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.irajnajafi1988gmail.hydrobell.R
import com.irajnajafi1988gmail.hydrobell.data.datastore.prefkeys.DishesPrefKeys
import com.irajnajafi1988gmail.hydrobell.data.datastore.provider.DishesPreferencesDataStore
import com.irajnajafi1988gmail.hydrobell.domain.datastore.repository.DishesRepository
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.model.ItemDishes
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DishesRepositoryImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
) : DishesRepository {
    override fun getSelectedDish(): Flow<ItemDishes> =
        context.DishesPreferencesDataStore.data.map { preferences ->
            val icon = preferences[DishesPrefKeys.SELECTED_DISH_ICON_KEY] ?: R.drawable.cup175
            val volume = preferences[DishesPrefKeys.SELECTED_DISH_VOLUME_KEY] ?: 175
            val label = preferences[DishesPrefKeys.SELECTED_DISH_LABEL_KEY] ?: "175 Ml"
            ItemDishes(icon = icon, volumeMl = volume, label = label)


        }

    override suspend fun saveDishes(dishes: ItemDishes) {
        context.DishesPreferencesDataStore.edit { prefs ->
            prefs[DishesPrefKeys.SELECTED_DISH_ICON_KEY] = dishes.icon
            prefs[DishesPrefKeys.SELECTED_DISH_VOLUME_KEY] = dishes.volumeMl
            prefs[DishesPrefKeys.SELECTED_DISH_LABEL_KEY] = dishes.label
        }

    }
}