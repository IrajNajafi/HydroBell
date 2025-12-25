package com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase

import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.DailyDrink
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.repository.DailyDrinkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import kotlin.text.insert


class UpsertUseCase @Inject constructor(
    private val repository: DailyDrinkRepository
) {
    suspend operator fun invoke(daily: DailyDrink) = repository.upsert(daily)
}

class GetByDateUseCase @Inject constructor(
    private val repository: DailyDrinkRepository
) {
    operator fun invoke(date: String): Flow<DailyDrink?> = repository.getByDate(date)
}


class GetAllUseCase @Inject constructor(
    private val repository: DailyDrinkRepository
) {
    operator fun invoke(): Flow<List<DailyDrink>> = repository.getAll()
}


class GetLatestUseCase @Inject constructor(
    private val repository: DailyDrinkRepository
) {
    operator fun invoke(): Flow<DailyDrink?> = repository.getLatest()
}

class ClearAllUseCase @Inject constructor(
    private val repository: DailyDrinkRepository
) {
    suspend operator fun invoke() = repository.clearAll()
}


class AddAmountToDailyDrinkUseCase @Inject constructor(
    private val repository: DailyDrinkRepository
) {

    suspend operator fun invoke(date: String, amount: Int): DailyDrink {

        val current = repository.getByDate(date).first()

        return if (current != null) {
            val updated = current.copy(
                totalDrink = current.totalDrink + amount
            )
            repository.upsert(updated)
            updated
        } else {
            val newDrink = DailyDrink(
                date = date,
                totalDrink = amount
            )
            repository.upsert(newDrink)
            newDrink
        }
    }
}

