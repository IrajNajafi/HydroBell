package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irajnajafi1988gmail.hydrobell.R
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.ActivityLevel
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Environment
import com.irajnajafi1988gmail.hydrobell.ui.theme.EnvFreezingStart
import com.irajnajafi1988gmail.hydrobell.ui.theme.EnvHotStart
import com.irajnajafi1988gmail.hydrobell.ui.theme.EnvNoneEnd
import com.irajnajafi1988gmail.hydrobell.ui.theme.EnvNormalEnd
import com.irajnajafi1988gmail.hydrobell.ui.theme.LevelExtremeBorder
import com.irajnajafi1988gmail.hydrobell.ui.theme.LevelHighBg
import com.irajnajafi1988gmail.hydrobell.ui.theme.LevelHighBorder
import com.irajnajafi1988gmail.hydrobell.ui.theme.LevelMediumBg
import com.irajnajafi1988gmail.hydrobell.ui.theme.LevelMediumBorder
import com.irajnajafi1988gmail.hydrobell.ui.theme.LevelSelectedBg
import com.irajnajafi1988gmail.hydrobell.ui.theme.LevelSelectedBorder
@Composable
fun LevelChipGroupPro(
    selectedOption: ActivityLevel,
    onSelectedChange: (ActivityLevel) -> Unit
) {
    val options = listOf(
        ActivityLevel.NONE,
        ActivityLevel.LOW,
        ActivityLevel.MEDIUM,
        ActivityLevel.HIGH,
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        options.forEach { item ->
            val isSelected = item == selectedOption

            val scale by animateFloatAsState(if (isSelected) 1.05f else 1f)
            val elevation by animateDpAsState(if (isSelected) 8.dp else 1.dp)

            // رنگ پس‌زمینه
            val bgColor = when (item) {
                ActivityLevel.NONE -> EnvNoneEnd
                ActivityLevel.LOW -> EnvFreezingStart
                ActivityLevel.MEDIUM -> EnvNormalEnd
                ActivityLevel.HIGH ->EnvHotStart
            }



            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(scale)
                    .shadow(elevation, RoundedCornerShape(18.dp))
                    .background(bgColor, RoundedCornerShape(18.dp))
                    .clickable { onSelectedChange(item) }
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ActivityLevelIcon(item)

                        Spacer(modifier = Modifier.width(12.dp))
                        val textColor by animateColorAsState(
                            targetValue = if (isSelected) Color.White else Color.Black,
                            label = "textColor"
                        )
                        Text(
                            text = stringResource(id = item.label),
                            fontSize = 18.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color =textColor
                        )
                    }

                    if (isSelected && item != ActivityLevel.NONE) {
                        Text(
                            text = "✔",
                            fontSize = 22.sp,
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ActivityLevelIcon(level: ActivityLevel) {
    val image = when (level) {
        ActivityLevel.NONE -> R.drawable.none
        ActivityLevel.LOW -> R.drawable.low
        ActivityLevel.MEDIUM -> R.drawable.medium
        ActivityLevel.HIGH -> R.drawable.high
    }

    Image(
        painter = painterResource(image),
        contentDescription = level.name,
        modifier = Modifier.size(25.dp)
    )
}
