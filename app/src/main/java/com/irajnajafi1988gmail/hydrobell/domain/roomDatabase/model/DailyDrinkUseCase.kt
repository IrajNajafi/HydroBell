package com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model

import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.GetAllHistoryUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.GetDrinkByDateUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.UpsertDailyDrinkUseCase

data class DailyDrinkUseCase(
    val upsertDailyDrinkUseCase: UpsertDailyDrinkUseCase,
    val getDrinkByDateUseCase: GetDrinkByDateUseCase,
    val getAllHistoryUseCase: GetAllHistoryUseCase
)
