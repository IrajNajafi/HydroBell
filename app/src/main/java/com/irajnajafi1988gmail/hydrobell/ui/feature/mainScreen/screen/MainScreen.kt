@file:Suppress("DEPRECATION")

package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.utils.AppDate
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.model.TableSection
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.viewmodel.MainScreenViewModel
import com.irajnajafi1988gmail.hydrobell.ui.theme.DeepSkyBlue
import com.irajnajafi1988gmail.hydrobell.ui.theme.LocalIsDarkTheme
import com.irajnajafi1988gmail.hydrobell.ui.theme.SkyBlue
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    navController: NavController
) {


    // ---------------- Data ----------------
    val tabs = TableSection.entries
    val pageCount = tabs.size



    // ---------------- Theme ----------------
    val isDarkMode = LocalIsDarkTheme.current
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = !isDarkMode
        )
    }

    // ---------------- State ----------------
    val selectedTab by viewModel.selectedTab.collectAsState()

    val pagerState = rememberPagerState(
        initialPage =selectedTab,
        pageCount = { pageCount }
    )

    val coroutineScope = rememberCoroutineScope()

    // Sync ViewModel with Pager
    LaunchedEffect(pagerState.currentPage) {
        viewModel.selectTab(pagerState.currentPage)
    }

    // ---------------- UI ----------------
    Column(modifier = Modifier.fillMaxSize()) {

        // -------- TabRow --------
        val selectedIndex =pagerState.currentPage

        TabRow(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding(),
            selectedTabIndex = selectedIndex,
            containerColor = if (isDarkMode) Color.Black else SkyBlue,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(
                        tabPositions[selectedIndex]
                    ),
                    color = if (isDarkMode) DeepSkyBlue else Color.White
                )
            }
        ) {
            tabs.forEachIndexed { index, section ->
                val isSelected = selectedIndex == index

                Tab(
                    selected = isSelected,
                    onClick = {
                        coroutineScope.launch {

                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(section.icon),
                                contentDescription = stringResource(section.label),
                                modifier = Modifier.size(24.dp),
                                tint = if (isSelected) {
                                    if (isDarkMode) Color.White else DeepSkyBlue
                                } else {
                                    if (isDarkMode) Color.Gray
                                    else Color.White.copy(alpha = 0.7f)
                                }
                            )
                            Spacer(Modifier.width(6.dp))
                            Text(
                                text = stringResource(id = section.label) ,
                                color = if (isSelected) Color.White
                                else if (isDarkMode) Color.Gray
                                else Color.White.copy(alpha = 0.7f)
                            )
                        }
                    }
                )
            }
        }

        // -------- Pager --------
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),

        ) { page ->
          val context = LocalContext.current
            val appDate = AppDate(context)
            when (page) {
                0 -> HomeScreen(navController)
                1 -> HistoryScreen(navController=navController, appDate =appDate ,)
                2 -> SettingScreen(navController)
            }
        }
    }
}
