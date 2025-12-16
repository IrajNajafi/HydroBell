package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.common.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomBottomBar(
    modifier: Modifier = Modifier,
    currentStep: Int,
    onClickBack: () -> Unit,
    onClickNext: () -> Unit,
    isNextEnabled: Boolean,
) {
    val gradient = Brush.horizontalGradient(
        listOf(
            Color(0x45A7CFFA),
            Color(0xFF26C6DA),
            Color(0xFFCAEEF5)
        )
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(10.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(gradient)
                    .padding(horizontal = 16.dp, vertical = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BackButton(
                    enabled = currentStep > 0,
                    onCliCkBack = onClickBack
                )
                NextButton(
                  enabled = isNextEnabled,
                    onClickNext = onClickNext,
                    text = if (currentStep<4) "Next" else "Finish"

                )

            }

        }
    }
}