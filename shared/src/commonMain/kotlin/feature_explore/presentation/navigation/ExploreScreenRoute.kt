package feature_explore.presentation.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import feature_explore.presentation.ExploreScreen

class ExploreScreenRoute : Screen {

    @Composable
    override fun Content() {
        ExploreScreen()
    }
}