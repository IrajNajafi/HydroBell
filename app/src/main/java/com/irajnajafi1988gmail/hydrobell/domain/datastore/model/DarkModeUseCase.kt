package com.irajnajafi1988gmail.hydrobell.domain.datastore.model

import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.GetDarkModeStateUseCase
import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.SaveDarkModeStateUseCase

data class DarkModeUseCase(
    val getDarkModeStateUseCase: GetDarkModeStateUseCase,
    val saveDarkModeStateUseCase: SaveDarkModeStateUseCase
)
