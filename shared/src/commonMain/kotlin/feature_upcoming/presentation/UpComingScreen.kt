package feature_upcoming.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import app.cash.paging.compose.LazyPagingItems
import core.domain.movie.Movie
import core.presentation.base.MovaInfoBottomSheetScaffold
import core.presentation.base.expandBottomSheet
import core.presentation.base.hideBottomSheet
import core.presentation.components.paging.MPagingColumnList
import core.presentation.theme.dimensions
import feature_upcoming.presentation.components.UpComingMovieItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpComingScreen(
    modifier: Modifier = Modifier,
    pagingMovies: LazyPagingItems<Movie>?,
    uiState: UpComingUiState,
    navigateToDetailScreen: () -> Unit,
    onEvent: (UpComingScreenEvent) -> Unit
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            skipHiddenState = false
        )
    )
    val coroutineScope = rememberCoroutineScope()

    MovaInfoBottomSheetScaffold(
        modifier = modifier.fillMaxSize(),
        scaffoldState = bottomSheetScaffoldState,
        selectedMovie = uiState.selectedMovie,
        selectedTvSeries = null,
        onClickCloseBottomSheet = { coroutineScope.hideBottomSheet(bottomSheetScaffoldState) },
        onClickedDetails = navigateToDetailScreen,
        topBar = { UpcomingScreenTopBar() },
        content = {
            MPagingColumnList(
                modifier = Modifier.padding(top = it.calculateTopPadding()).fillMaxSize(),
                pagingItems = pagingMovies,
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.fourLevel),
                paddingValues = PaddingValues(MaterialTheme.dimensions.fourLevel)
            ) { movie ->
                UpComingMovieItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { },
                    movie = movie,
                    onClickedInfo = {
                        coroutineScope.expandBottomSheet(bottomSheetScaffoldState)
                        onEvent(UpComingScreenEvent.OnClickInfo(movie = movie))
                    }
                )
            }
        }
    )
}

@Composable
private fun UpcomingScreenTopBar(
    modifier: Modifier = Modifier,
    title: String = "Coming Soon",
) {
    Text(
        modifier = modifier.padding(MaterialTheme.dimensions.fourLevel),
        text = title,
        style = MaterialTheme.typography.headlineMedium,
    )
}