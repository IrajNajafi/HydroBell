package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irajnajafi1988gmail.hydrobell.R
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Environment
import com.irajnajafi1988gmail.hydrobell.ui.theme.EnvColdEnd
import com.irajnajafi1988gmail.hydrobell.ui.theme.EnvColdStart
import com.irajnajafi1988gmail.hydrobell.ui.theme.EnvFreezingEnd
import com.irajnajafi1988gmail.hydrobell.ui.theme.EnvFreezingStart
import com.irajnajafi1988gmail.hydrobell.ui.theme.EnvHotEnd
import com.irajnajafi1988gmail.hydrobell.ui.theme.EnvHotStart
import com.irajnajafi1988gmail.hydrobell.ui.theme.EnvNoneEnd
import com.irajnajafi1988gmail.hydrobell.ui.theme.EnvNoneStart
import com.irajnajafi1988gmail.hydrobell.ui.theme.EnvNormalEnd
import com.irajnajafi1988gmail.hydrobell.ui.theme.EnvNormalStart
import com.irajnajafi1988gmail.hydrobell.ui.theme.EnvWarmEnd
import com.irajnajafi1988gmail.hydrobell.ui.theme.EnvWarmStart

@Composable
fun EnvironmentGrid(
    selectedOption: Environment,
    onSelectedChange: (Environment) -> Unit
) {
    val options = listOf(
        Environment.NONE,
        Environment.FREEZING,
        Environment.COLD,
        Environment.NORMAL,
        Environment.WARM,
        Environment.HOT,
    )



    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
         Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = options,
            key = { it.name }
        ) {item ->
            ItemEnvironment(
                item = item,
                selectedOption = selectedOption,
                onSelectedChange = { env ->
                    onSelectedChange(env)

                }
            )
        }
    }
}

@Composable
fun ItemEnvironment(
    item: Environment,
    selectedOption: Environment,
    onSelectedChange: (Environment) -> Unit,
    fixedHeight: Dp = 80.dp
) {
    val isSelected = item == selectedOption

    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.05f else 1f,
        animationSpec = tween(300, easing = FastOutSlowInEasing),
        label = "scale"
    )

    val rotation by animateFloatAsState(
        targetValue = if (isSelected) 2f else 0f,
        animationSpec = tween(300, easing = FastOutSlowInEasing),
        label = "rotation"
    )

    val (color1, color2) = when (item) {
        Environment.NONE -> EnvNoneStart to EnvNoneEnd
        Environment.FREEZING -> EnvFreezingStart to EnvFreezingEnd
        Environment.COLD -> EnvColdStart to EnvColdEnd
        Environment.NORMAL -> EnvNormalStart to EnvNormalEnd
        Environment.WARM -> EnvWarmStart to EnvWarmEnd
        Environment.HOT -> EnvHotStart to EnvHotEnd
    }

    val glow = color2.copy(alpha = 0.5f)
    val gradient = Brush.horizontalGradient(listOf(color1, color2))

    Box(
        modifier = Modifier
            .height(fixedHeight)
            .scale(scale)
            .graphicsLayer { rotationZ = rotation }
            .shadow(
                elevation = if (isSelected) 28.dp else 6.dp,
                shape = RoundedCornerShape(24.dp),
                ambientColor = glow,
                spotColor = glow
            )
            .clip(RoundedCornerShape(24.dp))
            .background(gradient)
            .clickable { onSelectedChange(item) }
            .padding(18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color.White.copy(0.35f)),
                    contentAlignment = Alignment.Center
                ) {
                    EnvironmentIcon(item)
                }

                Spacer(Modifier.width(12.dp))

                val textColor by animateColorAsState(
                    targetValue = if (isSelected) Color.White else Color.Black,
                    label = "textColor"
                )

                Text(
                    text = stringResource(item.label) ,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    maxLines = 1
                )
            }

            if (isSelected && item != Environment.NONE) {
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
private fun Environment.displayName(): String = when (this) {
    Environment.NONE -> "None"
    Environment.FREEZING -> "Freezing"
    Environment.COLD -> "Cold"
    Environment.NORMAL -> "Normal"
    Environment.WARM -> "Warm"
    Environment.HOT -> "Hot"
}


@Composable
fun EnvironmentIcon(environment: Environment) {

    val iconRes = when (environment) {
        Environment.NONE -> R.drawable.none
        Environment.FREEZING -> R.drawable.freezing
        Environment.COLD -> R.drawable.cold
        Environment.NORMAL -> R.drawable.ic_normal
        Environment.WARM -> R.drawable.warm
        Environment.HOT -> R.drawable.very_hot
    }


    Image(
        painter = painterResource(iconRes),
        contentDescription = environment.name,
        modifier = Modifier.size(28.dp)
    )
}
