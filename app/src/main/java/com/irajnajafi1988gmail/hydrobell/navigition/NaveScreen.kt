package com.irajnajafi1988gmail.hydrobell.navigition

sealed class NaveScreen (val route: String){
    object MainSetupScreen: NaveScreen("main_setup_Screen")
    object MainScreen : NaveScreen("main_Screen")

}