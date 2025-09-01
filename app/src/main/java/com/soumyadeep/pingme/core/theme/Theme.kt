package com.soumyadeep.pingme.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
// Remove 'Shapes' if you haven't defined it yet, or import it if you have
// import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color // Good to have for clarity if using Color.White directly

// Explicitly import your custom colors
import com.soumyadeep.pingme.core.theme.PrimaryBlue
import com.soumyadeep.pingme.core.theme.OnPrimary // This one specifically
import com.soumyadeep.pingme.core.theme.SecondaryTeal // This one specifically
import com.soumyadeep.pingme.core.theme.BackgroundDark
import com.soumyadeep.pingme.core.theme.SurfaceDark
import com.soumyadeep.pingme.core.theme.OnBackgroundDark
import com.soumyadeep.pingme.core.theme.OnSurfaceDark
import com.soumyadeep.pingme.core.theme.BackgroundLight
import com.soumyadeep.pingme.core.theme.SurfaceLight
import com.soumyadeep.pingme.core.theme.OnBackgroundLight
import com.soumyadeep.pingme.core.theme.OnSurfaceLight
// If Typography is also custom and in this package, import it too
// import com.soumyadeep.pingme.core.theme.Typography


val DarkColors = darkColorScheme(
    primary = PrimaryBlue,
    onPrimary = OnPrimary, // Make sure OnPrimary is resolvable
    secondary = SecondaryTeal, // Make sure SecondaryTeal is resolvable
    background = BackgroundDark,
    surface = SurfaceDark,
    onBackground = OnBackgroundDark,
    onSurface = OnSurfaceDark
)

val LightColors = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = OnPrimary,
    secondary = SecondaryTeal,
    background = BackgroundLight,
    surface = SurfaceLight,
    onBackground = OnBackgroundLight,
    onSurface = OnSurfaceLight
)

@Composable
fun PingMeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !darkTheme

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = colorScheme.background,
            darkIcons = useDarkIcons
        )
        systemUiController.setNavigationBarColor(
            color = colorScheme.background,
            darkIcons = useDarkIcons
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Ensure Typography is defined and imported
        shapes = Shapes,       // Ensure Shapes is defined and imported
        content = content
    )
}
    