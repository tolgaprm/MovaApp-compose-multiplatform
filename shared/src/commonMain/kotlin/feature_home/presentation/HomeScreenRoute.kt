package feature_home.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object HomeScreenRoute : Tab {

    @Composable
    override fun Content() {
        HomeScreen()
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Rounded.Home)
            return remember {
                TabOptions(
                    icon = icon,
                    title = "Home",
                    index = 0u
                )
            }
        }

    fun unselectedIcon(): ImageVector {
        return Icons.Outlined.Home
    }
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Text("Home Screen")
}