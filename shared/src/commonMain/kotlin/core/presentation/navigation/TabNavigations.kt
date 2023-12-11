package core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import feature_explore.presentation.navigation.ExploreScreenRoute
import feature_home.presentation.navigation.HomeScreenRoute
import feature_mylist.presentation.navigation.MyListScreenRoute
import feature_upcoming.presentation.navigation.UpComingScreenRoute
import kotlin.jvm.Transient

private class TabData(@Transient private val tab: TabItem, private val screen: Screen) : Tab {
    override val key: ScreenKey
        get() = tab.key

    @Composable
    override fun Content() {
        Navigator(screen) {
            SlideTransition(it)
        }
    }

    override val options: TabOptions
        @Composable get() {
            val icon = rememberVectorPainter(tab.selectedIcon)
            val title = tab.title

            return remember {
                TabOptions(index = 0u, title = title, icon = icon)
            }
        }
}

val TabItem.tab: Tab
    get() = TabData(this, tabContent)

private val TabItem.tabContent: Screen
    get() {
        return when (this) {
            is TabItem.HomeTab -> HomeScreenRoute()
            is TabItem.ExploreTab -> ExploreScreenRoute()
            is TabItem.UpcomingTab -> UpComingScreenRoute
            is TabItem.MyListTab -> MyListScreenRoute()
        }
    }


@Composable
fun CurrentTab() {
    val tabNavigator = LocalTabNavigator.current
    val currentTab = tabNavigator.current

    tabNavigator.saveableState("currentTab") {
        currentTab.Content()
    }
}