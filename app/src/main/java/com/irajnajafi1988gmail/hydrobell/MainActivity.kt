package com.irajnajafi1988gmail.hydrobell

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.irajnajafi1988gmail.hydrobell.navigition.AppNavigation
import com.irajnajafi1988gmail.hydrobell.navigition.NaveScreen
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.screen.HomeScreen
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.screen.MainScreen
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.screen.SettingScreen
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.screen.MainSetupScreen
import com.irajnajafi1988gmail.hydrobell.ui.theme.HydroBellTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HydroBellTheme {
               AppNavigation()

         //     MainScreen(navController = rememberNavController())
            }
        }
    }
}
