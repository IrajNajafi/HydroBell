package com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model

import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.AddAmountToDailyDrinkUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.ClearAllUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.GetAllUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.GetByDateUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.GetLatestUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.ResetTodayDrinkUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.UpsertUseCase

data class DailyDrinkUseCase(
    val upsert: UpsertUseCase,
    val getByDate: GetByDateUseCase,
    val getAll: GetAllUseCase,
    val getLatest: GetLatestUseCase,
    val clearAll: ClearAllUseCase,
    val addAmountToDailyDrinkUseCase: AddAmountToDailyDrinkUseCase,
    val resetTodayDrinkUseCase: ResetTodayDrinkUseCase
)
