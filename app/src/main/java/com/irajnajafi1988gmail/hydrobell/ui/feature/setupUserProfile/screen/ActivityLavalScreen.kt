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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.irajnajafi1988gmail.hydrobell.R
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.components.LevelChipGroupPro
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.ActivityLevel
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Gender

@Composable
fun ActivityLavalScreen(
    modifier: Modifier = Modifier,
    gender: Gender,
    onSelectedChange: (ActivityLevel) -> Unit,
    selectedActivityLevel: ActivityLevel
) {

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(if (gender == Gender.MALE) R.raw.man_activity else R.raw.woman_activity)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Please Select Your Activity Laval",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.weight(1f))
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(200.dp)


        )
        LevelChipGroupPro(
            selectedOption = selectedActivityLevel,
            onSelectedChange = { level ->
                onSelectedChange(level)

            }
        )

        Spacer(modifier = Modifier.weight(1f))

    }

}