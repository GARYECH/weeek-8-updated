package com.example.week8.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val GruvBlack = Color(0xFF1D2021)
val GruvYellow = Color(0xFFD79921)
val GruvGray = Color(0xFF3C3836)

private val DarkColors = darkColorScheme(
    primary = GruvYellow,
    background = GruvBlack,
    surface = GruvGray,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun ArtistTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = DarkColors, content = content)
}
