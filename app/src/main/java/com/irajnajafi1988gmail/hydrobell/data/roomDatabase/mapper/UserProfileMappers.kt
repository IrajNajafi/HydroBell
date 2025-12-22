package com.irajnajafi1988gmail.hydrobell.data.roomDatabase.mapper

import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.entities.UserProfileEntity
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.UserProfile

fun UserProfile.toEntity(): UserProfileEntity{
    return UserProfileEntity(
        id = id,
        gender = gender,
        weight = weight,
        age = age,
        activityLaval =activityLaval,
        environment = environment
    )

}

fun UserProfileEntity.toDomain(): UserProfile{
    return UserProfile(
        id = id,
        gender = gender,
        weight = weight,
        age = age,
        activityLaval =activityLaval,
        environment = environment
    )
}