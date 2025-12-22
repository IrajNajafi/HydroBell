package com.irajnajafi1988gmail.hydrobell.data.roomDatabase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.db.AppDatabase
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.ActivityLevel
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Environment
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Gender

@Entity(tableName = AppDatabase.USERPROFILE_TABLE)
data class UserProfileEntity(
    @PrimaryKey
    val id: Int = 1,
    val gender: Gender = Gender.NONE,
    val weight: Int = 0,
    val age: Int = 0,
    val activityLaval: ActivityLevel = ActivityLevel.NONE,
    val environment: Environment = Environment.NONE
)