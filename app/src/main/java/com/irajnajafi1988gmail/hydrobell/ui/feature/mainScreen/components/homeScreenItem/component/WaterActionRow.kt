package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irajnajafi1988gmail.hydrobell.R
import com.irajnajafi1988gmail.hydrobell.ui.theme.DeepSkyBlue

@Composable
fun WaterActionRow(

) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.arrowup),
                contentDescription = null,
                modifier = Modifier
                    .rotate(-90f)
                    .size(22.dp),
                tint = DeepSkyBlue
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.confirm_that_you_have_just_drunk_water),
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }


    }
}



