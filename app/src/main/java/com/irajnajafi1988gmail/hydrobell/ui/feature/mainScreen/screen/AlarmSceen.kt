package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.screen

import android.app.TimePickerDialog
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.*
import com.irajnajafi1988gmail.hydrobell.R
import kotlinx.coroutines.delay

@Composable
fun AlarmScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var wakePlaying by remember { mutableStateOf(false) }
    var sleepPlaying by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) {
            wakePlaying = true
            sleepPlaying = true

            delay(1500)
            wakePlaying = false
            sleepPlaying = false

            delay(4500)
        }
    }

    BackHandler {
        navController.popBackStack()
    }
    var wakeUpTime by rememberSaveable { mutableStateOf("07:00") }
    var sleepTime by rememberSaveable { mutableStateOf("23:00") }

    val wakeComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.automatic_time))
    val sleepComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_time_sleep))

    val wakeProgress by animateLottieCompositionAsState(
        composition = wakeComposition,
        iterations = 1,
        isPlaying = wakePlaying,
        restartOnPlay = true
    )

    val sleepProgress by animateLottieCompositionAsState(
        composition = sleepComposition,
        iterations = 1,
        isPlaying = sleepPlaying,
        restartOnPlay = true
    )


    val wakeImage = R.drawable.waking_up
    val sleepImage = R.drawable.sleep_image

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
           .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.please_select_your_wake_up_and_sleep_time_),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 50.dp)
        )

        Spacer(Modifier.height(40.dp))

        AlarmCard(
            title = stringResource(R.string.wake_up_Time),
            time = wakeUpTime,
            imageRes = wakeImage,
            backgroundColor = Color(0xFFE3F2FD),
            titleColor = Color(0xFF0D47A1),
            onTimeSelected = { wakeUpTime = it },
            lottieComposition = wakeComposition,
            lottieProgress = wakeProgress
        )

        AlarmCard(
            title = stringResource(R.string.sleep_Time),
            time = sleepTime,
            imageRes = sleepImage,
            backgroundColor = Color(0xFFFFF3E0),
            titleColor = Color(0xFFF57C00),
            onTimeSelected = { sleepTime = it },
            lottieComposition = sleepComposition,
            lottieProgress = sleepProgress
        )

        Spacer(modifier = Modifier.weight(1f))


        SaveButton {
            println("WakeUp: $wakeUpTime, Sleep: $sleepTime saved!")
        }

        Spacer(Modifier.height(24.dp))
    }
}


@Composable
fun AlarmCard(
    title: String,
    time: String,
    imageRes: Int,
    backgroundColor: Color,
    titleColor: Color,
    onTimeSelected: (String) -> Unit,
    lottieComposition: LottieComposition?,
    lottieProgress: Float
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = titleColor
                )

                Image(
                    painter = painterResource(imageRes),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )


                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        val parts = time.split(":")
                        TimePickerDialog(
                            context,
                            { _, h, m ->
                                onTimeSelected("%02d:%02d".format(h, m))
                            },
                            parts[0].toInt(),
                            parts[1].toInt(),
                            true
                        ).show()
                    }
                ) {
                    Text("$title: $time", fontWeight = FontWeight.SemiBold)
                }
            }


            lottieComposition?.let {
                LottieAnimation(
                    composition = it,
                    progress = { lottieProgress },
                    modifier = Modifier.size(100.dp)
                )
            }
        }
    }
}

@Composable
fun SaveButton(onClick: () -> Unit) {
    var isPressed by remember { mutableStateOf(false) }

    Button(
        onClick = {
            isPressed = true
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF42A5F5),
            contentColor = Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = if (isPressed) 12.dp else 8.dp,
            pressedElevation = 16.dp
        )
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF42A5F5),
                            Color(0xFF478DE0)
                        )
                    ),
                    shape = RoundedCornerShape(30.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.save_Alarm),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
