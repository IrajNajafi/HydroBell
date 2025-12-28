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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.irajnajafi1988gmail.hydrobell.R
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.component.DishesBox
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.component.RefreshIcon
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.component.TItemMessage
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.component.WaterActionRow
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.component.WaterProgressCard
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.model.ItemDishes
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.viewModel.DailyDrinkViewModel
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.viewModel.DishesViewModel
import com.irajnajafi1988gmail.hydrobell.ui.theme.BluePrimary
import com.irajnajafi1988gmail.hydrobell.ui.theme.LevelMediumBorder
import com.irajnajafi1988gmail.hydrobell.ui.theme.LevelHighBorder

@Composable
fun HomeScreen(
    navController: NavController,
    dailyDrink: DailyDrinkViewModel = hiltViewModel(),
    dishesViewModel: DishesViewModel = hiltViewModel()
) {
    // ------------------------------
    // 1️⃣ Collect today's drink data from DailyDrinkViewModel
    // ------------------------------
    val todayDrink by dailyDrink.todayDrink.collectAsState()
    val dailyNeed by dailyDrink.dailyNeed.collectAsState()
    val drunkWater = todayDrink?.totalDrink ?: 0

    // ------------------------------
    // 2️⃣ Collect the selected dish and its properties
    // ------------------------------
    val selectedDish by dishesViewModel.selectedDish.collectAsState()
    val selectedIcon = selectedDish.icon
    val selectedLabel = selectedDish.label
    val selectedVolume = selectedDish.volumeMl.coerceAtLeast(1)

    // ------------------------------
    // 3️⃣ Define available cups (dishes)
    // ------------------------------
    val itemDishes = listOf(
        ItemDishes(R.drawable.cup100, "100 ml", 100),
        ItemDishes(R.drawable.cup125, "125 ml", 125),
        ItemDishes(R.drawable.cup175, "175 ml", 175),
        ItemDishes(R.drawable.cup200, "200 ml", 200),
        ItemDishes(R.drawable.cup250, "250 ml", 250),
        ItemDishes(R.drawable.cup500, "500 ml", 500),
        ItemDishes(R.drawable.cup1000, "1000 ml", 1000)
    )

    // ------------------------------
    // 4️⃣ Calculate drinking status using a helper function
    // ------------------------------
    val statusMessage = calculateDrinkStatus(drunkWater, dailyNeed)

    // ------------------------------
    // 5️⃣ Calculate cups consumed vs daily goal
    // ------------------------------
    val cupsText = calculateCupsText(drunkWater, dailyNeed, selectedVolume)

    // ------------------------------
    // 6️⃣ Observe dishes popup visibility
    // ------------------------------
    val showDishes by dishesViewModel.showDishes.collectAsState()

    // ------------------------------
    // 7️⃣ Main Scaffold with floating button
    // ------------------------------
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .shadow(elevation = 20.dp, shape = RoundedCornerShape(8.dp)),
                onClick = { /* TODO: handle alarm */ },
                containerColor = Color.White
            ) {
                Image(
                    painter = painterResource(R.drawable.alarm),
                    contentDescription = "Alarm",
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ------------------------------
            // 8️⃣ Display status message (water progress tip)
            // ------------------------------
            TItemMessage(
                text = statusMessage.first,
                color = statusMessage.second
            )

            Spacer(Modifier.height(20.dp))

            // ------------------------------
            // 9️⃣ Water progress card (click to add selected volume)
            // ------------------------------
            WaterProgressCard(
                drunkWater = drunkWater,
                dailyWaterMl = dailyNeed,
                number = "$drunkWater / $dailyNeed Ml",
                text = cupsText,
                numberMl = selectedLabel,
                selectedIcon = selectedIcon,
                onClick = { dailyDrink.addAmount(selectedVolume) },
                modifier = Modifier
            )

            // ------------------------------
            // 10️⃣ Water action row (predefined actions like 100ml, 200ml)
            // ------------------------------
            WaterActionRow()

            // ------------------------------
            // 11️⃣ Refresh / select dishes
            // ------------------------------
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RefreshIcon(
                    onShowDishes = { dishesViewModel.toggleDishes() },
                    selectedIcon = selectedIcon,
                    label = selectedLabel
                )
            }

            // ------------------------------
            // 12️⃣ Reset button for today and dishes
            // ------------------------------
            Button(onClick = {
                dailyDrink.resetToday()
                dishesViewModel.resetDishes()
            }) {
                Text("Reset Drunk Water")
            }
        }

        // ------------------------------
        // 13️⃣ Dishes popup overlay
        // ------------------------------
        if (showDishes) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { dishesViewModel.toggleDishes() },
                contentAlignment = Alignment.Center
            ) {
                DishesBox(
                    itemDishes = itemDishes,
                    onSelected = { item ->
                        dishesViewModel.selectDish(item)
                        dishesViewModel.toggleDishes()
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
private fun calculateDrinkStatus(drunk: Int, goal: Int): Pair<String, Color> {
    return when {
        goal == 0 || drunk == 0 -> "Start drinking your first glass of water" to BluePrimary
        drunk < goal -> "Keep going! ${goal - drunk} ml to reach your goal" to BluePrimary
        drunk <= (goal * 1.1).toInt() -> "You reached your daily water goal 💦" to LevelMediumBorder
        else -> "You exceeded your goal by ${drunk - goal} ml!" to LevelHighBorder
    }
}

// ------------------------------
// Helper function to calculate cups text
// ------------------------------
private fun calculateCupsText(drunk: Int, goal: Int, volume: Int): String {
    val cupsDrunk = drunk / volume
    val cupsGoal = goal / volume
    return "$cupsDrunk cups / $cupsGoal cups"
}
