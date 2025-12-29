package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.components

import androidx.compose.runtime.Composable
import com.irajnajafi1988gmail.hydrobell.R
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.common.loadingscreen.LoadingScreenWithWave
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.ActivityLevel
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Environment
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Gender
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.screen.ActivityLavalScreen
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.screen.AgeScreen
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.screen.EnvironmentScreen
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.screen.GenderScreen
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.screen.WeightScreen

@Composable
fun SetupStepContent(
    step: Int,
    gender: Gender,
    weight: Int,
    age: Int,
    activity: ActivityLevel,
    environment: Environment,
    onGenderSelected: (Gender) -> Unit,
    onWeightChanged: (Int) -> Unit,
    onAgeChanged: (Int) -> Unit,
    onActivityChanged: (ActivityLevel) -> Unit,
    onEnvironmentChanged: (Environment) -> Unit
) {
    when (step) {
        0 -> GenderScreen(
            selectedGender = gender,
            onSelect = onGenderSelected
        )

        1 -> WeightScreen(
            imag = if (gender == Gender.MALE)
                R.drawable.weight_man
            else
                R.drawable.weight_woman,
            weight = weight,
            label = "Kg",
            onValueChange = onWeightChanged
        )

        2 -> AgeScreen(
            imag = if (gender == Gender.MALE)
                R.drawable.age_man
            else
                R.drawable.age_woman,
            age = age,
            label = "Yr",
            onValueChange = onAgeChanged
        )

        3 -> ActivityLavalScreen(
            selectedActivityLevel = activity,
            onSelectedChange = onActivityChanged,
            gender = gender

        )

        4 -> EnvironmentScreen(
            selectedOption = environment,
            onSelectedChange = onEnvironmentChanged
        )

        5 -> LoadingScreenWithWave()
    }
}
