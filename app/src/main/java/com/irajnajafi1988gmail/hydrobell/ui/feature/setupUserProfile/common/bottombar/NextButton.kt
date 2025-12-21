package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.common.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NextButton(
    onClickNext: () -> Unit,
    enabled: Boolean,
    text: String
) {
    val activeGradient = Brush.horizontalGradient(
        listOf(Color(0xFF1E88E5),
            Color(0xFF26C6DA)
        )
    )

    val disabledColor = Color(0xFFBBDEFB)
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(
                if (enabled)
                activeGradient
                else
                    Brush.horizontalGradient(listOf(disabledColor, disabledColor))
            )  .clickable(enabled = enabled) {onClickNext ()}
            .padding(horizontal = 28.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center


            ) {
        Text(
            text = text,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }

}