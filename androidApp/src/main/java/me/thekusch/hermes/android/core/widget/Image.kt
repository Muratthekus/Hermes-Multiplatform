package me.thekusch.hermes.android.core.widget

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import me.thekusch.hermes.android.ui.theme.BlackBlue
import me.thekusch.hermes.android.ui.theme.White

@Composable
fun getFieldIconTint(): Color {
    val darkTheme = isSystemInDarkTheme()

    return if (darkTheme) White else BlackBlue
}