package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.irajnajafi1988gmail.hydrobell.navigition.NaveScreen
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.viewmodel.MainScreenViewModel

@Composable
fun SettingScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel()
) {
    val state = mainScreenViewModel.state.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(vertical = 50.dp),
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "RestartData",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 15.dp)
                .clickable {
                    Log.d("RESET_UI", "👆 دکمه ریست توسط کاربر زده شد")

                    mainScreenViewModel.restartUserProfile()
                }
        )
    }

    // 👇 فقط وقتی ریست واقعاً تموم شد
    if (state.value.isResetDone) {
        navController.navigate(NaveScreen.MainSetupScreen.route) {
            popUpTo(NaveScreen.MainScreen.route) { inclusive = true }
        }
    }
}
