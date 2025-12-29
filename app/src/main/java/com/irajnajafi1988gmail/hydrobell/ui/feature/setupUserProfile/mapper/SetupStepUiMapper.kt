package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.mapper

import com.irajnajafi1988gmail.hydrobell.R
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.UserProfile
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.common.topBar.StepItem
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.*
import com.irajnajafi1988gmail.hydrobell.ui.theme.turquoise

object SetupStepUiMapper {

    fun map(form: UserProfile): List<StepItem> = listOf(
        StepItem(
            icon = when (form.gender) {
                Gender.MALE -> R.drawable.male
                Gender.FEMALE -> R.drawable.female
                else -> R.drawable.venus_mars_icon
            },
            label = form.gender.name,
            color = turquoise
        ),
        StepItem(
            icon = R.drawable.weight,
            label = "${form.weight} Kg",
            color = turquoise
        ),
        StepItem(
            icon = R.drawable.age,
            label = "${form.age} years",
            color = turquoise
        ),
        StepItem(
            icon = R.drawable.activity,
            label = form.activityLaval.name,
            color = turquoise
        ),
        StepItem(
            icon = R.drawable.environment,
            label = form.environment.name,
            color = turquoise
        )
    )
}
