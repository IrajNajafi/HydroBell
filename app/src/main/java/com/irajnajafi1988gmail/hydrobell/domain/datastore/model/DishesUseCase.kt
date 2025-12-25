package com.irajnajafi1988gmail.hydrobell.domain.datastore.model

import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.GetSelectedDishUseCase
import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.RestartDishesUseCase
import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.SaveDishesUseCase

data class DishesUseCase(
    val getSelectedDishUseCase :GetSelectedDishUseCase,
    val saveDishesUseCase: SaveDishesUseCase,
    val restartDishesUseCase : RestartDishesUseCase
)
