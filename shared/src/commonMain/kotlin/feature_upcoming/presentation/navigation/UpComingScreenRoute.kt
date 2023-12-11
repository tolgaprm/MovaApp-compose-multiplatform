package feature_upcoming.presentation.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import feature_upcoming.presentation.UpComingScreen

object UpComingScreenRoute : Screen {

    @Composable
    override fun Content() {
        UpComingScreen()
    }
}