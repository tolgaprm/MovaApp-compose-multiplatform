package feature_home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import core.domain.movie.models.Movie
import feature_home.presentation.components.NowPlayingSection
import feature_home.presentation.components.PopularMoviesSection

object HomeScreenRoute : Tab {

    @Composable
    override fun Content() {
        val homeScreenModel = getScreenModel<HomeScreenModel>()
        val nowPlayingMovies = homeScreenModel.nowPlayingMovies.data?.collectAsLazyPagingItems()
        val popularMovies = homeScreenModel.popularMovies.data?.collectAsLazyPagingItems()
        HomeScreen(nowPlayingMovies = nowPlayingMovies, popularMovies = popularMovies)
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
    nowPlayingMovies: LazyPagingItems<Movie>?,
    popularMovies: LazyPagingItems<Movie>?
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NowPlayingSection(nowPlayingMovies = nowPlayingMovies)

        PopularMoviesSection(popularMovies = popularMovies)
    }
}