package core.presentation.tabNavigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator

@Composable
fun RowScope.TabNavigationItem(
    tab: Tab,
    unselectedIcon: ImageVector? = null
) {
    val tabNavigator: TabNavigator = LocalTabNavigator.current
    val isSelectedTab = tabNavigator.current == tab

    NavigationBarItem(
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary
        ),
        selected = isSelectedTab,
        onClick = { tabNavigator.current = tab },
        icon = {
            if (!isSelectedTab && unselectedIcon != null) {
                UnSelectedIcon(imageVector = unselectedIcon, contentDescription = tab.options.title)
            } else {
                tab.options.icon?.let { icon ->
                    SelectedIcon(painter = icon, contentDescription = tab.options.title)
                }
            }
        },
        label = {
            AnimatedVisibility(
                visible = isSelectedTab
            ) {
                Text(tab.options.title)
            }
        }
    )
}

@Composable
private fun UnSelectedIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String
) {
    Icon(
        modifier = modifier,
        imageVector = imageVector,
        contentDescription = contentDescription
    )
}

@Composable
private fun SelectedIcon(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String
) {
    Icon(
        modifier = modifier,
        painter = painter,
        contentDescription = contentDescription
    )
}