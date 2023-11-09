import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

@Composable
fun MainView() {
    val isDarkTheme = isSystemInDarkTheme()
    App(
        darkTheme = isDarkTheme,
        dynamicColor = false
    )
}
