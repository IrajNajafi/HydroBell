package com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase

import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.DailyDrink
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.repository.DailyDrinkRepository
import javax.inject.Inject


class UpsertDailyDrinkUseCase @Inject constructor(
    private val repository: DailyDrinkRepository
) {
    suspend operator fun invoke(daily: DailyDrink) = repository.upsertDailyDrink(daily)
}

class GetDrinkByDateUseCase @Inject constructor(
    private val repository: DailyDrinkRepository
) {
    suspend operator fun invoke(date: String) = repository.getDrinkByDate(date)
}


class GetAllHistoryUseCase @Inject constructor(
    private val repository: DailyDrinkRepository
) {
    suspend operator fun invoke() = repository.getAllHistory()
}