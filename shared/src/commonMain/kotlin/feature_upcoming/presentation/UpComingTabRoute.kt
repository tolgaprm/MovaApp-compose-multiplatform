package feature_upcoming.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Upcoming
import androidx.compose.material.icons.rounded.Upcoming
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object UpComingTabRoute : Tab {

    @Composable
    override fun Content() {
        UpComingScreen()
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Rounded.Upcoming)
            return remember {
                TabOptions(
                    icon = icon,
                    title = "UpComing",
                    index = 2u
                )
            }
        }

    fun unselectedIcon(): ImageVector {
        return Icons.Outlined.Upcoming
    }
}

@Composable
private fun UpComingScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Upcoming Screen",
            modifier = Modifier.padding(it)
        )
    }
}