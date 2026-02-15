package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.utils

import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.UserProfile
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.ActivityLevel
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Environment
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Gender


object WaterCalculatorDynamic {


    fun calculateDailyNeedMl(user: UserProfile): Int {


        var baseMl = user.weight * 30f

        baseMl *= when (user.gender) {
            Gender.MALE -> 1.10f
            Gender.FEMALE -> 1.00f
            Gender.NONE -> 1.00f
        }

        val ageFactor = when {
            user.age < 10 -> 0.80f
            user.age in 10..17 -> 0.90f
            user.age in 18..55 -> 1.00f
            user.age > 55 -> 0.95f
            else -> 1.00f
        }
        baseMl *= ageFactor

        val activityAddedMl = when (user.activityLaval) {
            ActivityLevel.LOW, ActivityLevel.NONE -> 0f
            ActivityLevel.MEDIUM -> 400f
            ActivityLevel.HIGH -> 800f
        }


        val envAddedMl = when (user.environment) {
            Environment.FREEZING -> 100f
            Environment.COLD, Environment.NONE -> 0f
            Environment.NORMAL -> 200f
            Environment.WARM -> 300f
            Environment.HOT -> 400f
        }

        val subtotalMl = baseMl + activityAddedMl + envAddedMl


        val sweatPercentActivity = when (user.activityLaval) {
            ActivityLevel.LOW, ActivityLevel.NONE -> 0f
            ActivityLevel.MEDIUM -> 5f
            ActivityLevel.HIGH -> 10f
        }

        val sweatPercentEnv = when (user.environment) {
            Environment.FREEZING, Environment.COLD, Environment.NONE -> 0f
            Environment.NORMAL -> 5f
            Environment.WARM -> 10f
            Environment.HOT -> 15f
        }


        val totalSweatPercent = (sweatPercentActivity + sweatPercentEnv) / 100f

        val totalMl = subtotalMl * (1 + totalSweatPercent)

        val roundedMl = (totalMl / 250).toInt() * 250

        return roundedMl.coerceIn(1200, 5000)
    }
}