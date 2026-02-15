package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.screen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.irajnajafi1988gmail.hydrobell.navigition.NaveScreen
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.viewModel.DailyDrinkViewModel
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.viewModel.DishesViewModel
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.component.LoadingReset
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.component.SettingContent
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.component.SettingRow
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.model.SettingItem
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.viewModel.SettingViewModel
import kotlinx.coroutines.launch

@Composable
fun SettingScreen(
    navController: NavController,
    settingViewModel: SettingViewModel = hiltViewModel(),
    dailyDrink: DailyDrinkViewModel = hiltViewModel(),
    dishesViewModel: DishesViewModel = hiltViewModel(),
) {
    BackHandler {

        navController.navigate(NaveScreen.MainScreen.route) {

            popUpTo(NaveScreen.MainScreen.route) { inclusive = true }
        }
    }
    val state by settingViewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
    ) {


        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            SettingContent(
                onItemClick = { item ->
                    scope.launch {
                        // drawerState.close()
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            state.error?.let { error ->
                Text(
                    text = "خطا: $error",
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }


        if (state.isLoading) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoadingReset()
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Resetting...",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 20.sp
                )
            }
        }
    }

    LaunchedEffect(state.isDone) {
        if (state.isDone) {
            navController.navigate(NaveScreen.MainSetupScreen.route) {
                popUpTo(NaveScreen.MainScreen.route) { inclusive = true }
            }
        }
    }
}
