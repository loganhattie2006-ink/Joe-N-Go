package com.example.joego.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.joego.ui.theme.Cream

private val DarkColorScheme = darkColorScheme(
    primary = Green,
    secondary = LightGreen,
    tertiary = Grey,
    background = Black, // background
    surface = Brown,

    onPrimary = DarkGreen,
    onSecondary = DarkGreen,
    onTertiary = Cream,
    onBackground = Cream,
    onSurface = Cream,
)

private val LightColorScheme = lightColorScheme(
    primary = Green, // Nav bar colors
    secondary = DarkGreen, // darker color over nav bar
    tertiary = Brown, // logos
    background = Cream, // background
    surface = LightGreen,

    onPrimary = DarkGreen,
    onSecondary = Cream,
    onTertiary = Cream,
    onBackground = DarkGreen,
    onSurface = DarkGreen,
)

@Composable
fun JoeGoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Dynamic color ignores the custom theme and takes from the user's wallpaper.
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}