package feature_home.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import core.domain.movie.Movie
import core.domain.tvseries.TvSeries
import core.presentation.util.collectAsStateWithLifecycleM
import feature_home.presentation.components.HomeScreenBottomSheet
import feature_home.presentation.components.HomeScreenContent
import kotlinx.coroutines.launch

class HomeTabRoute(
    val onNavigateToDetail: (Int?) -> Unit = {},
) : Tab {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val homeScreenModel = getScreenModel<HomeScreenModel>()
        val nowPlayingMovies = homeScreenModel.nowPlayingMovies.data?.collectAsLazyPagingItems()
        val popularMovies = homeScreenModel.popularMovies.data?.collectAsLazyPagingItems()
        val topRatedMovies = homeScreenModel.topRatedMovies.data?.collectAsLazyPagingItems()
        val popularTvSeries = homeScreenModel.popularTvSeries.data?.collectAsLazyPagingItems()
        val topRatedTvSeries = homeScreenModel.topRatedTvSeries.data?.collectAsLazyPagingItems()

        val uiState = homeScreenModel.state.collectAsStateWithLifecycleM()

        val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = rememberStandardBottomSheetState(
                skipHiddenState = false
            )
        )
        val coroutineScope = rememberCoroutineScope()

        HomeScreen(nowPlayingMovies = nowPlayingMovies,
            popularMovies = popularMovies,
            topRatedMovies = topRatedMovies,
            popularTvSeries = popularTvSeries,
            topRatedTvSeries = topRatedTvSeries,
            scaffoldState = bottomSheetScaffoldState,
            selectedMovie = uiState.selectedMovie,
            selectedTvSeries = uiState.selectedTvSeries,
            onClickedMovie = { movie ->
                homeScreenModel.onEvent(HomeScreenEvent.OnMovieSelected(movie))
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                }
            },
            onClickedTvSeries = { tvSeries ->
                homeScreenModel.onEvent(HomeScreenEvent.OnTvSeriesSelected(tvSeries))
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                }
            },
            onClickCloseBottomSheet = {
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.hide()
                }
            },
            onClickedDetails = {
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.hide()
                }

                onNavigateToDetail(uiState.selectedMovie?.id ?: uiState.selectedTvSeries?.id)
            }
        )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    nowPlayingMovies: LazyPagingItems<Movie>?,
    popularMovies: LazyPagingItems<Movie>?,
    topRatedMovies: LazyPagingItems<Movie>?,
    popularTvSeries: LazyPagingItems<TvSeries>?,
    topRatedTvSeries: LazyPagingItems<TvSeries>?,
    scaffoldState: BottomSheetScaffoldState,
    selectedMovie: Movie? = null,
    selectedTvSeries: TvSeries? = null,
    onClickedMovie: (Movie) -> Unit,
    onClickedTvSeries: (TvSeries) -> Unit,
    onClickCloseBottomSheet: () -> Unit,
    onClickedDetails: () -> Unit
) {
    BottomSheetScaffold(modifier = modifier.fillMaxSize(),
        sheetPeekHeight = 0.dp,
        sheetContent = {
            HomeScreenBottomSheet(
                modifier = Modifier.fillMaxWidth(),
                selectedMovie = selectedMovie,
                selectedTvSeries = selectedTvSeries,
                onClickClose = onClickCloseBottomSheet,
                onClickedDetails = onClickedDetails
            )
        },
        sheetContainerColor = MaterialTheme.colorScheme.background,
        sheetContentColor = MaterialTheme.colorScheme.onBackground,
        scaffoldState = scaffoldState,
        content = {
            HomeScreenContent(
                nowPlayingMovies = nowPlayingMovies,
                popularMovies = popularMovies,
                topRatedMovies = topRatedMovies,
                popularTvSeries = popularTvSeries,
                topRatedTvSeries = topRatedTvSeries,
                onClickedMovie = onClickedMovie,
                onClickedTvSeries = onClickedTvSeries
            )
        }
    )
}