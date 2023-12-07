package feature_mylist.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object MyListTabRoute : Tab {

    @Composable
    override fun Content() {
        MyListScreen()
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Rounded.Bookmark)
            return remember {
                TabOptions(
                    icon = icon,
                    title = "My List",
                    index = 3u
                )
            }
        }

    fun unselectedIcon(): ImageVector {
        return Icons.Outlined.BookmarkBorder
    }
}

@Composable
private fun MyListScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "MyList Screen",
            modifier = Modifier.padding(it)
        )
    }
}
