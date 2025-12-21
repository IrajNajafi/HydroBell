package com.irajnajafi1988gmail.hydrobell.navigition

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.screen.MainSetupScreen
import com.irajnajafi1988gmail.hydrobell.ui.feature.splash.screen.SplashScreenContent

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NaveScreen.SplashScreenContent.route
    ){
      composable (route = NaveScreen.SplashScreenContent.route){
          SplashScreenContent(navController = navController)
      }
        composable(NaveScreen.MainSetupScreen.route) {
            MainSetupScreen()
        }
    }
}