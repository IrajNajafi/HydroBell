package com.irajnajafi1988gmail.hydrobell.data.roomDatabase.mapper

import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.entities.DailyDrinkEntity
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.DailyDrink

fun DailyDrink.toEntity(): DailyDrinkEntity{
    return DailyDrinkEntity(
        date = date,
        totalDrink = totalDrink,
        isCompleted = isCompleted

    )
}

fun DailyDrinkEntity.toDomain(): DailyDrink{
    return DailyDrink(
        date = date,
        totalDrink = totalDrink,
        isCompleted = isCompleted

    )
}
