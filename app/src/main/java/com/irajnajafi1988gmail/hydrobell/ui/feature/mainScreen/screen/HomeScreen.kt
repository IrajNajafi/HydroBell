package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.irajnajafi1988gmail.hydrobell.R
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.RefreshIcon
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.ToolTip
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.WaterActionRow
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.WaterProgressCard

@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(
floatingActionButton = {
    FloatingActionButton(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .shadow(elevation = 20.dp, shape = RoundedCornerShape(8.dp)),
        onClick = {  },
        containerColor = Color.White
    ) {
        Image(
            painter = painterResource(R.drawable.alarm),
            contentDescription = null,
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
            ToolTip(
                text = "Start drinking your first glass of water"
            )
            Spacer(Modifier.height(20.dp))
            WaterProgressCard(
                drunkWater = 0,
                dailyWaterMl = 0,
                number = "0",
                text = "Test",
                numberMl = "0 Ml",
                selectedIcon = R.drawable.drop,
                onClick = {},
                modifier = Modifier
            )
            WaterActionRow()
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ){
                RefreshIcon(
                    onShowDishes = {},
                    selectedIcon = R.drawable.drop,
                    label ="170.ml"
                )
            }

        }

    }
}