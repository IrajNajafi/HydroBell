package com.irajnajafi1988gmail.hydrobell.data.roomDatabase.repository

import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.dao.DailyDrinkDao
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.mapper.toDomain
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.mapper.toEntity
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.DailyDrink
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.repository.DailyDrinkRepository
import javax.inject.Inject

class DailyDrinkRepositoryImpl @Inject constructor(
    private val dailyDrinkDao: DailyDrinkDao
) : DailyDrinkRepository {
    override suspend fun upsertDailyDrink(daily: DailyDrink) {
        dailyDrinkDao.upsertDailyDrink(daily.toEntity())
    }

    override suspend fun getDrinkByDate(date: String): DailyDrink? {
        return dailyDrinkDao.getDrinkByDate(date)?.toDomain()
    }

    override suspend fun getAllHistory(): List<DailyDrink> {
        return dailyDrinkDao.getAllHistory().map { it.toDomain() }
    }
}