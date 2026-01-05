package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.irajnajafi1988gmail.hydrobell.R
import com.irajnajafi1988gmail.hydrobell.navigition.NaveScreen
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.common.toColor
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.component.DishesBox
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.component.RefreshIcon
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.component.TItemMessage
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.component.WaterActionRow
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.component.WaterProgressCard
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.model.WaterState
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.model.calculateWaterState
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.utils.DrinkItems
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.viewModel.DailyDrinkViewModel
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.viewModel.DishesViewModel
import kotlinx.coroutines.delay
import java.nio.file.WatchEvent

@Composable
fun HomeScreen(
    navController: NavController,
    dailyDrink: DailyDrinkViewModel = hiltViewModel(),
    dishesViewModel: DishesViewModel = hiltViewModel()
) {

    /* ------------------------------ */
    /* 🎬 Lottie (FAB animation) */
    /* ------------------------------ */

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.time)
    )

    var playAnimation by remember { mutableStateOf(false) }

    // فقط یک بار موقع ورود
    LaunchedEffect(Unit) {
        playAnimation = true
        delay(1200)
        playAnimation = false
    }

    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = playAnimation,
        restartOnPlay = true
    )

    /* ------------------------------ */
    /* 🧹 Clean UI state ON ENTER */
    /* ------------------------------ */

    LaunchedEffect(Unit) {
        dishesViewModel.closeDishes()
    }

    /* ------------------------------ */
    /* 📊 Collect States */
    /* ------------------------------ */

    val todayDrink by dailyDrink.todayDrink.collectAsState()
    val dailyNeed by dailyDrink.dailyNeed.collectAsState()

    val selectedDish by dishesViewModel.selectedDish.collectAsState()
    val showDishes by dishesViewModel.showDishes.collectAsState()

    val drunkWater = todayDrink?.totalDrink ?: 0
    val selectedIcon = selectedDish.icon
    val selectedLabel = selectedDish.label
    val selectedVolume = selectedDish.volumeMl.coerceAtLeast(1)

    /* ------------------------------ */
    /* 🧠 UI Logic */
    /* ------------------------------ */

    val (statusText, statusState) =
        calculateDrinkStatus(drunkWater, dailyNeed)

    val cupsText =
        calculateCupsText(drunkWater, dailyNeed, selectedVolume)

    /* ------------------------------ */
    /* 🧱 UI */
    /* ------------------------------ */

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .shadow(25.dp, CircleShape),
                containerColor = Color.White,
                onClick = {

                    navController.navigate(NaveScreen.AlarmScreen.route) {
                        launchSingleTop = true
                    }
                }
            ) {
                LottieAnimation(
                    composition = composition,
                    progress = { progress },
                    modifier = Modifier.size(60.dp)
                )
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TItemMessage(
                text = statusText,
                color = statusState.toColor()
            )

            Spacer(Modifier.height(20.dp))

            WaterProgressCard(
                drunkWater = drunkWater,
                dailyWaterMl = dailyNeed,
                number = "$drunkWater / $dailyNeed Ml",
                text = cupsText,
                numberMl = selectedLabel,
                selectedIcon = selectedIcon,
                onClick = { dailyDrink.addAmount(selectedVolume) }
            )

            WaterActionRow()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                RefreshIcon(
                    onShowDishes = dishesViewModel::toggleDishes,
                    selectedIcon = selectedIcon,
                    label = selectedLabel
                )
            }
        }

        /* ------------------------------ */
        /* 🍽 Dishes Overlay */
        /* ------------------------------ */

        if (showDishes) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        dishesViewModel.closeDishes()
                    },
                contentAlignment = Alignment.Center
            ) {
                DishesBox(
                    itemDishes = DrinkItems.itemDishes,
                    onSelected = {
                        dishesViewModel.selectDish(it)
                        dishesViewModel.closeDishes()
                    }
                )
            }
        }
    }
}


// ------------------------------
// Helper function to calculate status message and color
// Returns Pair(text, color)
// ------------------------------

fun calculateDrinkStatus(
    drunk: Int,
    goal: Int
): Pair<String, WaterState> {

    val state = calculateWaterState(drunk, goal)

    val text = when (state) {
        WaterState.START ->
            "Start drinking your first glass of water"

        WaterState.NORMAL ->
            "Keep going! ${goal - drunk} ml to reach your goal"

        WaterState.GOAL ->
            "You reached your daily water goal 💦"

        WaterState.OVER ->
            "You exceeded your goal by ${drunk - goal} ml!"
    }

    return text to state
}


// ------------------------------
// Helper function to calculate cups text
// ------------------------------
private fun calculateCupsText(drunk: Int, goal: Int, volume: Int): String {
    val cupsDrunk = drunk / volume
    val cupsGoal = goal / volume
    return "$cupsDrunk cups / $cupsGoal cups"
}
