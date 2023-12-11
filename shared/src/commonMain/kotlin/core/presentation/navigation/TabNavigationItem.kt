package core.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun RowScope.TabNavigationItem(
    tabItem: TabItem,
    selected: Boolean,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary
        ),
        selected = selected,
        onClick = onClick,
        icon = {
            val icon = if (selected) tabItem.selectedIcon else tabItem.unSelectedIcon
            Icon(
                imageVector = icon,
                contentDescription = tabItem.title
            )
        },
        label = {
            AnimatedVisibility(
                visible = selected
            ) {
                Text(tabItem.title)
            }
        }
    )
}