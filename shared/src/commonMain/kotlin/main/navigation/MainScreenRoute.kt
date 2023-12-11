package main.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.TabNavigator
import core.presentation.navigation.TabItem
import core.presentation.navigation.tab
import core.presentation.util.collectAsStateWithLifecycleM
import core.presentation.util.viewModel
import main.MainScreen
import main.MainViewModel

class MainScreenRoute : Screen {
    @Composable
    override fun Content() {
        val mainViewModel = viewModel<MainViewModel>()
        val tabItems = mainViewModel.tabItems.collectAsStateWithLifecycleM()
        TabNavigator(TabItem.HomeTab().tab, true) { navigator ->
            MainScreen(
                tabItems = tabItems,
                isTabSelected = {
                    navigator.current.key == it.key
                },
                onTabSelected = {
                    navigator.current = it.tab
                }
            )
        }
    }
}
