package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.TableInfo
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.irajnajafi1988gmail.hydrobell.R
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.components.EnvironmentGrid
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Environment
import kotlinx.coroutines.delay

@Composable
fun EnvironmentScreen(
    modifier: Modifier = Modifier,
    selectedOption: Environment,
    onSelectedChange: (Environment) -> Unit

) {

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.environment)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1
    )


    Column(
        modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Please Select Your Environment",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.weight(1f))

        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp)
        )

        EnvironmentGrid(
            selectedOption = selectedOption,
            onSelectedChange = { environment ->
                onSelectedChange(environment)
            }
        )

        Spacer(modifier = Modifier.weight(1f))
    }

}