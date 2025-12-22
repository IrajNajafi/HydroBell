package com.irajnajafi1988gmail.hydrobell.domain.datastore.model

import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.ActivityLevel
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Environment
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Gender

data class UserProfile(
    val id: Int = 1,
    val gender: Gender = Gender.NONE,
    val weight: Int = 0,
    val age: Int = 0,
    val activityLaval: ActivityLevel = ActivityLevel.NONE,
    val environment: Environment = Environment.NONE
)
