package ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun MovaTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme {
        content()
    }
}