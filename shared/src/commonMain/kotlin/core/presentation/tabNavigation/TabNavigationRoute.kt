package core.presentation.tabNavigation


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import feature_home.presentation.HomeScreenRoute

object TabNavigationRoute : Screen {

    @Composable
    override fun Content() {
        TabNavigator(HomeScreenRoute) {
            Scaffold(
                bottomBar = {
                    NavigationBar(
                        modifier = Modifier.fillMaxWidth(),
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    ) {
                        TabNavigationItem(HomeScreenRoute)
                    }
                }
            ) {
                CurrentTab()
            }
        }
    }
}