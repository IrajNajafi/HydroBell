package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemsGender(
    onClickImage: () -> Unit,
    image: Int,
    label: String,
    isSelectAlpha: Boolean,
    isSelectFontSize: Boolean
) {

    val alpha = if (isSelectAlpha) 1f else 0.3f

    val imageSize by animateDpAsState(
        targetValue = if (isSelectFontSize) 200.dp else 160.dp
    )
    Column(
        modifier = Modifier
            .clickable { onClickImage() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier
                .size(imageSize)
                .alpha(alpha)

        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = label,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray.copy(alpha = alpha)
        )
    }
}