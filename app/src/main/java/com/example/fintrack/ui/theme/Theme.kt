package com.example.fintrack.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = primaryDark, //
    onPrimary = onPrimaryDark, //
    primaryContainer = primaryContainerDark, //
    onPrimaryContainer = onPrimaryContainerDark, //
    inversePrimary = inversePrimaryDark, //
    secondary = secondaryDark, //
    onSecondary = onSecondaryDark, //
    secondaryContainer = secondaryContainerDark, //
    onSecondaryContainer = onSecondaryContainerDark, //
    tertiary = tertiaryDark, //
    onTertiary = onTertiaryDark, //
    tertiaryContainer = tertiaryContainerDark, //
    onTertiaryContainer = onTertiaryContainerDark, //
    background = backgroundDark, //
    onBackground = onBackgroundDark, //
    surface = surfaceDark, //
    onSurface = onSurfaceDark, //
    surfaceVariant = surfaceVariantDark, //
    onSurfaceVariant = onSurfaceVariantDark, //
    inverseSurface = inverseSurfaceDark, //
    inverseOnSurface = inverseOnSurfaceDark, //
    error = errorDark, //
    onError = onErrorDark, //
    errorContainer = errorContainerDark, //
    onErrorContainer = onErrorContainerDark, //
    outline = outlineDark, //
    outlineVariant = outlineVariantDark, //
    scrim = scrimDark, //
    surfaceBright = surfaceBrightDark, //
    surfaceContainer = surfaceContainerDark, //
    surfaceContainerHigh = surfaceContainerHighDark, //
    surfaceContainerHighest = surfaceContainerHighestDark, //
    surfaceContainerLow = surfaceContainerLowDark, //
    surfaceContainerLowest = surfaceContainerLowestDark, //
    surfaceDim = surfaceDimDark //
)

private val LightColorScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight, //
    primaryContainer = primaryContainerLight, //
    onPrimaryContainer = onPrimaryContainerLight, //
    inversePrimary = inversePrimaryLight, //
    secondary = secondaryLight, //
    onSecondary = onSecondaryLight, //
    secondaryContainer = secondaryContainerLight, //
    onSecondaryContainer = onSecondaryContainerLight, //
    tertiary = tertiaryLight, //
    onTertiary = onTertiaryLight, //
    tertiaryContainer = tertiaryContainerLight, //
    onTertiaryContainer = onTertiaryContainerLight, //
    background = backgroundLight, //
    onBackground = onBackgroundLight, //
    surface = surfaceLight, //
    onSurface = onSurfaceLight, //
    surfaceVariant = surfaceVariantLight, //
    onSurfaceVariant = onSurfaceVariantLight, //
    inverseSurface = inverseSurfaceLight, //
    inverseOnSurface = inverseOnSurfaceLight, //
    error = errorLight, //
    onError = onErrorLight, //
    errorContainer = errorContainerLight, //
    onErrorContainer = onErrorContainerLight, //
    outline = outlineLight, //
    outlineVariant = outlineVariantLight, //
    scrim = scrimLight, //
    surfaceBright = surfaceBrightLight, //
    surfaceContainer = surfaceContainerLight, //
    surfaceContainerHigh = surfaceContainerHighLight, //
    surfaceContainerHighest = surfaceContainerHighestLight, //
    surfaceContainerLow = surfaceContainerLowLight, //
    surfaceContainerLowest = surfaceContainerLowestLight, //
    surfaceDim = surfaceDimLight //
)

@Composable
fun FinTrackTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}