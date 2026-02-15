package com.irajnajafi1988gmail.hydrobell.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.model.ItemDarkMode

val LocalIsDarkTheme = staticCompositionLocalOf { false }
// -------------------- Dark Colors --------------------
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

// -------------------- Light Colors --------------------
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

// -------------------- Theme --------------------
@Composable
fun HydroBellTheme(
    darkMode: ItemDarkMode = ItemDarkMode.SYSTEM,
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    val isDarkTheme = when (darkMode) {
        ItemDarkMode.DARK -> true
        ItemDarkMode.LIGHT -> false
        ItemDarkMode.SYSTEM -> isSystemInDarkTheme()
    }

    //  ColorScheme
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (isDarkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        isDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    androidx.compose.runtime.CompositionLocalProvider(
        LocalIsDarkTheme provides isDarkTheme
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
