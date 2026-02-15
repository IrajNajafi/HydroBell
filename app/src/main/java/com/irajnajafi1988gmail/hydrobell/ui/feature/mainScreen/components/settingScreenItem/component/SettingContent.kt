package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.irajnajafi1988gmail.hydrobell.R
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.viewModel.DailyDrinkViewModel
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.viewModel.DishesViewModel
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.model.ItemDarkMode
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.model.ItemLanguage
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.model.SettingItem
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.viewModel.DarkModeViewModel
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.viewModel.LanguageViewModel
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.viewModel.SettingViewModel

@Composable
fun SettingContent(
    onItemClick: (SettingItem) -> Unit,
    settingViewModel: SettingViewModel = hiltViewModel(),
    dailyDrink: DailyDrinkViewModel = hiltViewModel(),
    dishesViewModel: DishesViewModel = hiltViewModel(),
    darkModeViewModel: DarkModeViewModel = hiltViewModel(),
    languageViewModel: LanguageViewModel = hiltViewModel()
) {
    val expandedStates = remember { mutableStateMapOf<SettingItem, Boolean>() }
    val darkMode by darkModeViewModel.selectedDarkMode.collectAsState()
    val selectLanguage by languageViewModel.selectedLanguage.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 40.dp)
    ) {
        SettingItem.items.forEach { item ->
            SettingRow(
                iconRes = item.iconRes,
                titleRes = item.titleRes,
                expanded = expandedStates[item] == true,
                onClick = { expandedStates[item] = !(expandedStates[item] ?: false) }

            )
            AnimatedVisibility(
                visible = expandedStates[item] == true,
                enter = expandVertically(), // Smooth expand
                exit = shrinkVertically()   // Smooth collapse
            ) {
                when (item) {
                    SettingItem.DARKMODE -> {
                        // Column containing dark mode options
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 56.dp,
                                    top = 4.dp,
                                    bottom = 8.dp
                                ) // Indent for sub-items
                        ) {
                            listOf(
                                ItemDarkMode.DARK,
                                ItemDarkMode.LIGHT,
                                ItemDarkMode.SYSTEM
                            ).forEach { mode ->

                                SettingSubRow(
                                    title = stringResource(id = mode.text),
                                    imageRes = mode.icon,
                                    imageCheckItem = R.drawable.tick,
                                    isChecked = darkMode == mode,
                                    onClick = { darkModeViewModel.saveDarkMode(mode) },
                                    iconSizeDp = 18.dp
                                )
                            }
                        }
                    }

                    SettingItem.LANGUAGE -> {

                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 56.dp, top = 4.dp, bottom = 8.dp)
                        ) {
                            ItemLanguage.entries.forEach { language ->
                                SettingSubRow(
                                    title = stringResource(id = language.textRes),
                                    imageRes = 0,
                                    trailingText = language.flag,
                                    imageCheckItem = R.drawable.tick,
                                    isChecked = selectLanguage == language,
                                    onClick = { languageViewModel.saveLanguage(language) },
                                    iconSizeDp = 18.dp,
                                )
                            }
                        }
                    }

                    SettingItem.RESET -> {
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 56.dp, top = 4.dp, bottom = 8.dp)
                        ) {
                            SettingSubRow(
                                title = stringResource(id = R.string.reset_data),
                                imageRes = R.drawable.reset_data,
                                imageCheckItem = null,
                                onClick = {
                                    settingViewModel.restartUserProfile()
                                    dailyDrink.clearAll()
                                    dishesViewModel.resetDishes()
                                },
                                iconSizeDp = 40.dp,
                                textSizeSp = 15.sp
                            )


                        }
                    }

                    SettingItem.INFO -> {
                        // Column for app info like contact and version
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 56.dp, top = 4.dp, bottom = 8.dp)
                        ) {
                            SettingSubRow(
                                title = "irajnajafi1988@gmail.com",
                                imageRes = R.drawable.gmail,
                                imageCheckItem = null,
                                onClick = {},
                                iconSizeDp = 18.dp,
                                textSizeSp = 10.sp
                            )

                            SettingSubRow(
                                title = stringResource(id = R.string.program_version_1_0_0),
                                imageRes = R.drawable.tag,
                                imageCheckItem = null,
                                onClick = {},
                                iconSizeDp = 15.dp,
                                textSizeSp = 10.sp
                            )
                        }
                    }

                }

            }


        }
    }
}