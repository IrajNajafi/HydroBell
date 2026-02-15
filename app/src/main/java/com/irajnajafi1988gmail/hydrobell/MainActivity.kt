package com.irajnajafi1988gmail.hydrobell

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.irajnajafi1988gmail.hydrobell.data.datastore.prefkeys.LanguagePrefKeys
import com.irajnajafi1988gmail.hydrobell.data.datastore.provider.LanguageDataStore
import com.irajnajafi1988gmail.hydrobell.navigition.AppNavigation
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.model.ItemDarkMode
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.model.ItemLanguage
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.utils.LanguageManager
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.viewModel.DarkModeViewModel
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.viewModel.LanguageViewModel
import com.irajnajafi1988gmail.hydrobell.ui.theme.HydroBellTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var languageCode: String

    override fun attachBaseContext(newBase: Context?) {
        languageCode = runBlocking {
            newBase?.LanguageDataStore?.data
                ?.map { pref ->
                    pref[LanguagePrefKeys.LANGUAGE_CODE]
                        ?: ItemLanguage.ENGLISH.code
                }
                ?.first() ?: ItemLanguage.ENGLISH.code
        }

        val updatedContext = newBase?.let {
            LanguageManager.applyLanguage(it, languageCode)
        }

        super.attachBaseContext(updatedContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val darkModeViewModel: DarkModeViewModel = hiltViewModel()
            val languageViewModel: LanguageViewModel = hiltViewModel()

            val darkMode by darkModeViewModel.selectedDarkMode.collectAsState(initial = null)
            val selectedLanguage by languageViewModel.selectedLanguage.collectAsState()

            val systemUiController = rememberSystemUiController()

            if (darkMode == null || selectedLanguage == null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
                return@setContent
            }

            LaunchedEffect(selectedLanguage) {
                if (languageCode != selectedLanguage!!.code) {
                    recreate()
                }
            }

            val useDarkIcons = darkMode != ItemDarkMode.DARK
            systemUiController.setStatusBarColor(
                color = androidx.compose.ui.graphics.Color.Transparent,
                darkIcons = useDarkIcons
            )
            systemUiController.setNavigationBarColor(
                color = androidx.compose.ui.graphics.Color.Transparent,
                darkIcons = useDarkIcons
            )

            HydroBellTheme(darkMode = darkMode!!) {
                AppNavigation(
                    languageCode = selectedLanguage!!.code
                )
            }
        }
    }
}
