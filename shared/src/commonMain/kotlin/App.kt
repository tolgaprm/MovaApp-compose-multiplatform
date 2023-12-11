import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import core.presentation.theme.MovaTheme
import feature_splash.presentation.SplashScreenRoute
import main.navigation.MainScreenRoute

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
            // if Android app, start with TabNavigationRoute, otherwise start with SplashScreenRoute
            // Because We implement the splash with splashScreenAPI in Android
            val startingRoute = if (androidApp) MainScreenRoute() else SplashScreenRoute()
            Navigator(startingRoute) {
                CurrentScreen()
            }
        }
    }
}