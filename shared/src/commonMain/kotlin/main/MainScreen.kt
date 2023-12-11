package main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.presentation.navigation.CurrentTab
import core.presentation.navigation.TabItem
import core.presentation.navigation.TabNavigationItem

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    tabItems: List<TabItem>,
    isTabSelected: (TabItem) -> Boolean,
    onTabSelected: (TabItem) -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ) {
                for (tabItem in tabItems) {
                    TabNavigationItem(
                        tabItem = tabItem,
                        selected = isTabSelected(tabItem),
                        onClick = { onTabSelected(tabItem) }
                    )
                }
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            CurrentTab()
        }
    }
}