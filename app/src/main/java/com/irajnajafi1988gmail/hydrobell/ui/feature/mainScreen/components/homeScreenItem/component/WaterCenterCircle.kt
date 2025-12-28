package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irajnajafi1988gmail.hydrobell.R
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.model.WaterUiStyle

@Composable
fun WaterCenterCircle(
    number: String,
    text: String,
    numberMl: String,
    icon: Int?,
    style: WaterUiStyle,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.size(250.dp),
        shape = CircleShape,
        color = style.centerBg,
        border = BorderStroke(2.dp, Color.LightGray),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.background(
                Brush.radialGradient(
                    colors = style.gradient,
                    radius = 300f
                ),
                shape = CircleShape
            ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(number, fontSize = 22.sp)
                Text(text, fontSize = 14.sp)

                icon?.let {
                    Icon(
                        painter = painterResource(it),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                    Text(numberMl, fontSize = 14.sp)
                }

                Image(
                    painter = painterResource(R.drawable.select),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}
