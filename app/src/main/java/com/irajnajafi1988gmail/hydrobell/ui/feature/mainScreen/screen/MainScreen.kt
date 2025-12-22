package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.model.TableSection
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.viewmodel.MainScreenViewModel
import com.irajnajafi1988gmail.hydrobell.ui.theme.DeepSkyBlue
import com.irajnajafi1988gmail.hydrobell.ui.theme.SkyBlue

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val selectedTab by viewModel.selectedTab.collectAsState()
    val tabs = TableSection.entries

    Column(modifier = Modifier.fillMaxSize()) {

        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = SkyBlue,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = Color.White
                )
            }
        ) {
            tabs.forEachIndexed { index, section ->
                Box(
                    modifier = Modifier
                        .background(SkyBlue)
                        .padding(vertical = 20.dp),
                    Alignment.Center
                ) {
                    Tab(
                        selected = selectedTab == index,
                        onClick = { viewModel.selectTab(index) },
                        text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Icon(
                                    painter = painterResource(section.icon),
                                    contentDescription = section.label,
                                    modifier = Modifier.size(24.dp),
                                    tint = if (selectedTab == index)
                                        DeepSkyBlue
                                    else
                                        Color.White
                                )
                                Spacer(modifier = Modifier.padding(6.dp))
                                Text(
                                    text = section.label,
                                    color = Color.White
                                )
                            }
                        }
                    )
                }
            }
        }
        when (selectedTab) {
            0 -> HomeScreen(navController = navController)
            1 -> HistoryScreen()
            2 -> SettingScreen(navController = navController)
        }
    }
}