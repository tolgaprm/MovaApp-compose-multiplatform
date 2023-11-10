import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import core.theme.MovaTheme
import ui.home.HomeScreenRoute
import ui.splash.SplashScreenRoute

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    androidApp: Boolean
) {
    MovaTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val startingRoute = if (androidApp) HomeScreenRoute() else SplashScreenRoute()
            Navigator(startingRoute) {
                SlideTransition(it)
            }
        }
    }
}