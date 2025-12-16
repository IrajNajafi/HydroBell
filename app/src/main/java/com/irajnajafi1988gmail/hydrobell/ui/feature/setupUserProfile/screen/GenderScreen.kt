package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.irajnajafi1988gmail.hydrobell.R
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.components.ItemsGender
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Gender
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.ItemGender

@Composable
fun GenderScreen(
    modifier: Modifier = Modifier,
    selectedGender: Gender,
    onSelect: (Gender) -> Unit
) {
    val itemImage = remember {
        listOf(
            ItemGender(R.drawable.man_male, "Male", Gender.MALE),
            ItemGender(R.drawable.woman_female, "Female", Gender.FEMALE)
        )
    }
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Please Select Your Gender.",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
        )
        Spacer(Modifier.weight(1f))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            itemImage.forEach { item ->
                ItemsGender(
                    onClickImage = { onSelect(item.gender) },
                    image = item.image,
                    label = item.label,
                    isSelectAlpha = selectedGender == item.gender,
                    isSelectFontSize = selectedGender == item.gender
                )
            }

        }
        Spacer(Modifier.weight(1f))

    }
}