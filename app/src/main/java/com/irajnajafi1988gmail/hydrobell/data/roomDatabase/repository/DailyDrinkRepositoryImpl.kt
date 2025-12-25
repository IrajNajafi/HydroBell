package com.irajnajafi1988gmail.hydrobell.data.roomDatabase.repository

import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.dao.DailyDrinkDao
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.mapper.toDomain
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.mapper.toEntity
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.DailyDrink
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.repository.DailyDrinkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DailyDrinkRepositoryImpl @Inject constructor(
    private val dailyDrinkDao: DailyDrinkDao
) : DailyDrinkRepository {
    override suspend fun upsert(daily: DailyDrink) {
        dailyDrinkDao.insert(daily.toEntity())
    }

    override fun getByDate(date: String): Flow<DailyDrink?> {
       return dailyDrinkDao.getByDate(date).map { it?.toDomain() }
    }


    override fun getAll(): Flow<List<DailyDrink>> {
       return dailyDrinkDao.getAll().map { lis-> lis.map { it.toDomain() } }
    }

    override fun getLatest(): Flow<DailyDrink?> {
        return dailyDrinkDao.getLatest().map { it?.toDomain() }
    }


    override suspend fun clearAll() {
       dailyDrinkDao.clearAll()
    }

}