package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irajnajafi1988gmail.hydrobell.R
import com.irajnajafi1988gmail.hydrobell.ui.theme.buttonDisabledContainerColor

@Composable
fun TItemMessage(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.White,
    fontSize: TextUnit = 12.sp
    ) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.drop_image),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
        )
        Surface(
            color = buttonDisabledContainerColor,
            shape = messageShape(arrowSize = 30f, cornerRadius = 30f, arrowOffsetY = 40f),
            modifier = modifier.padding(start = 10.dp)

        ) {
            Box(
                modifier = Modifier.padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = text, color = color, fontSize = fontSize)
            }
        }
    }
}