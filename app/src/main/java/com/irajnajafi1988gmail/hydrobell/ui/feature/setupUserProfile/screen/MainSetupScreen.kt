package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.common.bottombar.CustomBottomBar
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.common.topBar.MainSetupPath
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Gender
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.viewmodel.SetupUserProfileViewModel
import com.irajnajafi1988gmail.hydrobell.R


@Composable
fun MainSetupScreen(
    userProfile: SetupUserProfileViewModel = hiltViewModel()
) {


    val currentSetup by userProfile.currentSetup.collectAsState()
    val setGender by userProfile.selectedGender.collectAsState()
    val steps by userProfile.stepFlow.collectAsState()
    val selectedWeight by userProfile.selectedWeight.collectAsState()
    val selectedAge by userProfile.selectedAge.collectAsState()
    val selectedActivityLevel by userProfile.selectedActivity.collectAsState()
    val selectedOption by userProfile.selectedEnvironment.collectAsState()

    Scaffold(
        contentWindowInsets = WindowInsets.systemBars,
        topBar = {
            if (currentSetup < SetupUserProfileViewModel.LOADING_STEP) {
                MainSetupPath(
                    modifier = Modifier.statusBarsPadding(),
                    steps = steps,
                    currentStep = currentSetup
                )
            }

        },
        bottomBar = {

            if (currentSetup <= SetupUserProfileViewModel.LAST_FORM_STEP) {
                CustomBottomBar(
                    modifier = Modifier.navigationBarsPadding(),
                    onClickNext = {
                        userProfile.nextStep()
                    },
                    onClickBack = {
                        userProfile.backStep()
                    },
                    isNextEnabled = true,
                    currentStep = currentSetup
                )
            }
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (currentSetup) {
                0 -> {
                    GenderScreen(
                        modifier = Modifier,
                        selectedGender = setGender,
                        onSelect = { gender -> userProfile.setGender(gender) }
                    )
                }

                1 -> {
                    WeightScreen(
                        modifier = Modifier,
                        imag = if (setGender == Gender.MALE) R.drawable.weight_man else R.drawable.weight_woman,
                        weight = selectedWeight,
                        label = "Kg",
                        onValueChange = { weight ->
                            userProfile.setWeight(weight)
                        }

                    )
                }

                2 -> {
                    AgeScreen(
                        modifier = Modifier,
                        imag = if (setGender == Gender.MALE) R.drawable.age_man else R.drawable.age_woman,
                        age = selectedAge,
                        label = "Yr",
                        onValueChange = { age ->
                            userProfile.setAge(age)
                        }
                    )
                }

                3 -> {
                    ActivityLavalScreen(
                        modifier = Modifier,
                        gender = setGender,
                        selectedActivityLevel = selectedActivityLevel,
                        onSelectedChange = { level ->
                            userProfile.setActivityLevel(level)
                        }

                    )
                }

                4 -> {
                    EnvironmentScreen(
                        modifier = Modifier,
                        selectedOption = selectedOption,
                        onSelectedChange = {environment ->
                            userProfile.setEnvironment(environment)
                        }
                    )
                }
                5 -> {}
            }
        }
    }
}