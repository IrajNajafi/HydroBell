package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.common.bottombar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irajnajafi1988gmail.hydrobell.R
import com.irajnajafi1988gmail.hydrobell.ui.theme.buttonContainerColor
import com.irajnajafi1988gmail.hydrobell.ui.theme.buttonDisabledContainerColor

@Composable
fun BackButton(
    onCliCkBack: () -> Unit,
    enabled: Boolean
) {
    Button(
        onClick = onCliCkBack,
        enabled = enabled,
        shape = CircleShape,
        modifier = Modifier.size(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonContainerColor,
            contentColor = Color.White,
            disabledContainerColor = buttonDisabledContainerColor,
            disabledContentColor = Color.White,
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = stringResource(R.string.previous),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}