package core.theme

import androidx.compose.runtime.Composable

@Composable
expect fun MovaTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
)