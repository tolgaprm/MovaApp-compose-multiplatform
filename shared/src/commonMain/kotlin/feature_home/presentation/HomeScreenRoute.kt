package feature_home.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import core.domain.movie.models.Movie
import core.presentation.components.MPagingRowList

object HomeScreenRoute : Tab {

    @Composable
    override fun Content() {
        val homeScreenModel = getScreenModel<HomeScreenModel>()
        val nowPlayingMovies = homeScreenModel.nowPlayingMovies.data?.collectAsLazyPagingItems()
        HomeScreen(nowPlayingMovies = nowPlayingMovies)
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
    nowPlayingMovies: LazyPagingItems<Movie>?
) {
    MPagingRowList(
        modifier = modifier.fillMaxWidth(),
        pagingItems = nowPlayingMovies
    ) { movie ->
        Text(text = movie.genresBySeparatedByComma ?: "")
        Text(text = "----------")
    }
}