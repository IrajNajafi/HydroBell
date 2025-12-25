package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.annotations.Until
import com.irajnajafi1988gmail.hydrobell.navigition.NaveScreen
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.viewmodel.DailyDrinkViewModel
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.viewmodel.MainScreenViewModel
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.viewmodel.SettingViewModel
import kotlinx.coroutines.delay

@Composable
fun SettingScreen(
    navController: NavController,
    settingViewModel: SettingViewModel = hiltViewModel(),
    dailyDrink: DailyDrinkViewModel = hiltViewModel()
) {
    val state by settingViewModel.uiState.collectAsState()

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
                    settingViewModel.restartUserProfile()
                    dailyDrink.clearAll()
                }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (state.isLoading) {
            Text("در حال ریست...", modifier = Modifier.padding(16.dp))
        }

        state.error?.let { error ->
            Text("خطا: $error", color = Color.Red, modifier = Modifier.padding(16.dp))
        }
    }

    if (state.isDone) {
        LaunchedEffect(Unit) {
            navController.navigate(NaveScreen.MainSetupScreen.route) {
                popUpTo(NaveScreen.MainScreen.route) { inclusive = true }
            }
        }
    }


}
