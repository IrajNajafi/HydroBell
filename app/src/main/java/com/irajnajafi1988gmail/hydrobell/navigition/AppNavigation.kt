package com.irajnajafi1988gmail.hydrobell.navigition

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.component.AlarmScreen
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.screen.MainScreen
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.screen.MainSetupScreen
import com.irajnajafi1988gmail.hydrobell.ui.feature.splash.screen.SplashScreenContent
import com.irajnajafi1988gmail.hydrobell.ui.feature.splash.viewmodel.SplashScreenContentViewModel
import kotlinx.coroutines.delay

@Composable
fun AppNavigation(
    splashViewModel: SplashScreenContentViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val isProfileComplete by splashViewModel.isProfileComplete.collectAsState()


    LaunchedEffect(Unit) {
        delay(1500)
        when (isProfileComplete) {
            true -> navController.navigate(NaveScreen.MainScreen.route) {
                popUpTo(NaveScreen.SplashScreenContent.route) { inclusive = true }
            }
            false -> navController.navigate(NaveScreen.MainSetupScreen.route) {
                popUpTo(NaveScreen.SplashScreenContent.route) { inclusive = true }
            }
            null -> Unit
        }
    }

    NavHost(
        navController = navController,
        startDestination = NaveScreen.SplashScreenContent.route
    ) {
        composable(NaveScreen.SplashScreenContent.route) {
            SplashScreenContent() // فقط UI
        }

        composable(NaveScreen.MainSetupScreen.route) {
            MainSetupScreen(navController = navController)
        }

        composable(NaveScreen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable (NaveScreen.AlarmScreen.route){
            AlarmScreen(navController=navController)
        }
    }
}
