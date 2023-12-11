package core.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Upcoming
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Upcoming
import androidx.compose.ui.graphics.vector.ImageVector

sealed class TabItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
) {

    val key: String = this::class.simpleName!!

    class HomeTab : TabItem(
        title = "Home",
        selectedIcon = Icons.Rounded.Home,
        unSelectedIcon = Icons.Outlined.Home
    )

    class ExploreTab : TabItem(
        title = "Explore",
        selectedIcon = Icons.Rounded.Explore,
        unSelectedIcon = Icons.Outlined.Explore
    )

    class UpcomingTab : TabItem(
        title = "Upcoming",
        selectedIcon = Icons.Rounded.Upcoming,
        unSelectedIcon = Icons.Outlined.Upcoming
    )

    class MyListTab : TabItem(
        title = "My List",
        selectedIcon = Icons.Rounded.Bookmark,
        unSelectedIcon = Icons.Outlined.BookmarkBorder
    )
}