package feature_explore.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object ExploreScreenRoute : Tab {

    @Composable
    override fun Content() {
        ExploreScreen()
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Rounded.Explore)
            return remember {
                TabOptions(
                    icon = icon,
                    title = "Explore",
                    index = 1u
                )
            }
        }

    fun unselectedIcon(): ImageVector {
        return Icons.Outlined.Explore
    }
}

@Composable
private fun ExploreScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Explore Screen",
            modifier = Modifier.padding(it)
        )
    }
}