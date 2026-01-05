package com.irajnajafi1988gmail.hydrobell.navigition

sealed class NaveScreen (val route: String){
    object SplashScreenContent : NaveScreen("splash_screen_Content")
    object MainSetupScreen: NaveScreen("main_setup_Screen")
    object MainScreen : NaveScreen("main_Screen")
    object AlarmScreen : NaveScreen("alarm_Screen")

}