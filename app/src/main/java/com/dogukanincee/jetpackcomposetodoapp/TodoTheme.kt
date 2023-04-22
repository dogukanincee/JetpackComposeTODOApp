package com.dogukanincee.jetpackcomposetodoapp

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Color(0xFF6200EE),
    primaryVariant = Color(0xFF3700B3),
    secondary = Color(0xFF03DAC6)
)

/**
 * The com.dogukanincee.jetpackcomposetodoapp.TodoTheme function is a Composable function that provides a custom MaterialTheme for the
 * entire application. It sets the color scheme, typography, and shapes used in the application.
 *
 * @param content A lambda that represents the content of the theme. This should include all the
 *                Composable functions that will be displayed within the theme.
 */
@Composable
fun TodoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}