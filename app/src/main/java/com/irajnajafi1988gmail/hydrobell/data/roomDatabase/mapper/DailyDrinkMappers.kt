package com.irajnajafi1988gmail.hydrobell.data.roomDatabase.mapper

import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.entities.DailyDrinkEntity
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.DailyDrink

fun DailyDrink.toEntity(): DailyDrinkEntity{
    return DailyDrinkEntity(
        id = id,
        date = date,
        totalDrink = totalDrink
    )
}

fun DailyDrinkEntity.toDomain(): DailyDrink{
    return DailyDrink(
        id = id,
        date = date,
        totalDrink = totalDrink
    )
}
