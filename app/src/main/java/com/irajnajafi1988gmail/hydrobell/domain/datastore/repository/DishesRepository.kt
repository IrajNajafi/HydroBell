package com.irajnajafi1988gmail.hydrobell.domain.datastore.repository

import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.model.ItemDishes
import kotlinx.coroutines.flow.Flow

interface DishesRepository {
    fun getSelectedDish(): Flow<ItemDishes>
    suspend fun saveDishes(dishes: ItemDishes)
}