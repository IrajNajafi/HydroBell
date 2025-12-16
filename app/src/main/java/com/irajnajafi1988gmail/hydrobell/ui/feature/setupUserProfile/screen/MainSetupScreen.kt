package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.common.bottombar.CustomBottomBar
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.common.topBar.ItemPath
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.common.topBar.MainSetupPath
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.viewmodel.SetupUserProfileViewModel

@Composable
fun MainSetupScreen(
    userProfile: SetupUserProfileViewModel = hiltViewModel()
){


    val currentSetup by userProfile.currentSetup.collectAsState()
    val steps by userProfile.stepFlow.collectAsState()
    Scaffold (
        contentWindowInsets = WindowInsets.systemBars,
        topBar = {
            if (currentSetup<5) {
                MainSetupPath(
                    modifier = Modifier,
                    steps = steps,
                    currentStep = currentSetup
                )
            }

        },
        bottomBar = {
            CustomBottomBar(
                modifier = Modifier,
                onClickNext = {},
                onClickBack = {},
                isNextEnabled =true ,
                currentStep = currentSetup
            )

        }

    ){ paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            when(currentSetup){
                0->{}
                1->{}
                2->{}
                3->{}
                4->{}
                5->{}
            }
        }
    }
}