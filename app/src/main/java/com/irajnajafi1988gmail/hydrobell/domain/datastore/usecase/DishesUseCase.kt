package com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase

import com.irajnajafi1988gmail.hydrobell.domain.datastore.repository.DishesRepository
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.model.ItemDishes
import javax.inject.Inject

class GetSelectedDishUseCase @Inject constructor(
    private val repository: DishesRepository
) {
    operator fun invoke()= repository.getSelectedDish()
}

class SaveDishesUseCase @Inject constructor(
    private val repository: DishesRepository
){
    suspend operator fun invoke(dishes: ItemDishes) = repository.saveDishes(dishes)
}

class RestartDishesUseCase @Inject constructor(
    private val repository: DishesRepository
){
   suspend operator fun invoke() = repository.saveDishes(ItemDishes())
}