package core.presentation.tabNavigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import feature_detail.presentation.DetailScreenRoute
import feature_explore.presentation.ExploreTabRoute
import feature_home.presentation.HomeTabRoute
import feature_mylist.presentation.MyListTabRoute
import feature_upcoming.presentation.UpComingTabRoute

object TabNavigationRoute : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val homeTab = HomeTabRoute(
            onNavigateToDetail = { movieId, tvSeriesId ->
                navigator.push(
                    DetailScreenRoute(movieId = movieId, tvSeriesId = tvSeriesId)
                )
            }
        )

        TabNavigator(homeTab) {
            Scaffold(
                bottomBar = {
                    NavigationBar(
                        modifier = Modifier.fillMaxWidth(),
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    ) {
                        TabNavigationItem(homeTab, homeTab.unselectedIcon())
                        TabNavigationItem(ExploreTabRoute, ExploreTabRoute.unselectedIcon())
                        TabNavigationItem(UpComingTabRoute, UpComingTabRoute.unselectedIcon())
                        TabNavigationItem(MyListTabRoute, MyListTabRoute.unselectedIcon())
                    }
                },
                modifier = Modifier.fillMaxSize()
            ) {
                Surface(
                    modifier = Modifier
                        .padding(it)
                ) {
                    CurrentTab()
                }
            }
        }
    }
}