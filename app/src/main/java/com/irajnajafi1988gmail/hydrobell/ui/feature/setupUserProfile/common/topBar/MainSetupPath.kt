package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.common.topBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.common.topBar.StepItem

@Composable
fun MainSetupPath(
    modifier: Modifier = Modifier,
    steps: List<StepItem>,
    currentStep: Int

) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 30.dp, bottom = 20.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            steps.forEachIndexed { index, item ->
                val isActive = index <= currentStep
                ItemPath(
                    icon = item.icon,
                    label = item.label,
                    color = if (isActive) item.color else Color.LightGray
                )
                if (index < steps.lastIndex) {
                    StepProgressLine(
                        modifier = Modifier
                            .weight(1f)
                            .height(3.dp)
                            .padding(horizontal = 4.dp),
                        color = if (isActive) item.color else Color.LightGray,
                        progress = if (index < currentStep) 1f else 0f
                    )
                }
            }
        }
    }
}