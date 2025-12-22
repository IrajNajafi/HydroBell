package com.irajnajafi1988gmail.hydrobell.ui.feature.splash.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.irajnajafi1988gmail.hydrobell.R
import com.irajnajafi1988gmail.hydrobell.navigition.NaveScreen
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.common.loadingscreen.AnimatedWaveBackground
import kotlinx.coroutines.delay

@Composable
fun SplashScreenContent() {


    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.splash_loading)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        speed = 1.5f
    )

    Box(modifier = Modifier.fillMaxSize()) {

        AnimatedWaveBackground()

        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.BottomCenter)
        )
    }
}
