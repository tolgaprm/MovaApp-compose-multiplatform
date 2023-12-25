package feature_detail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.cash.paging.compose.collectAsLazyPagingItems
import core.domain.model.movie.Movie
import core.domain.model.tv.TvSeries
import core.presentation.base.MovaInfoBottomSheetScaffold
import core.presentation.base.expandBottomSheet
import core.presentation.base.hideBottomSheet
import core.presentation.components.BackButton
import core.presentation.components.ErrorView
import core.presentation.components.MCircularProgressIndicator
import core.presentation.theme.dimensions
import feature_detail.presentation.components.section.detail.MovieDetailSuccessView
import feature_detail.presentation.components.section.detail.TvDetailSuccessView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    uiState: DetailScreenUiState,
    onBackPressed: () -> Unit,
    onNavigateToPersonDetail: (Int) -> Unit,
    onEvent: (DetailScreenEvent) -> Unit
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            skipHiddenState = false
        )
    )
    val coroutineScope = rememberCoroutineScope()

    MovaInfoBottomSheetScaffold(
        modifier = modifier.fillMaxSize(),
        onClickCloseBottomSheet = { coroutineScope.hideBottomSheet(bottomSheetScaffoldState) },
        scaffoldState = bottomSheetScaffoldState,
        selectedMovie = uiState.getMovieSuccess()?.selectedRecommendationMovie,
        selectedTvSeries = uiState.getTvSeriesSuccess()?.selectedRecommendationTvSeries,
        onClickedDetails = {
            coroutineScope.hideBottomSheet(bottomSheetScaffoldState)

            val movieSuccess = uiState.getMovieSuccess()
            val tvSeriesSuccess = uiState.getTvSeriesSuccess()
            if (movieSuccess != null) {
                onEvent(DetailScreenEvent.GetMovieDetail(movieSuccess.selectedRecommendationMovie?.id))
            } else if (tvSeriesSuccess != null) {
                onEvent(DetailScreenEvent.GetTvSeriesDetail(tvSeriesSuccess.selectedRecommendationTvSeries?.id))
            }
        },
        content = {
            DetailScreenContent(
                uiState = uiState,
                onBackPressed = onBackPressed,
                onNavigateToPersonDetail = onNavigateToPersonDetail,
                onClickRetry = {
                    onEvent(DetailScreenEvent.Retry)
                },
                onClickedMovieRecommendationItem = { movie ->
                    coroutineScope.expandBottomSheet(bottomSheetScaffoldState)
                    onEvent(DetailScreenEvent.OnClickRecommendationMovie(movie))
                },
                onClickedTvRecommendationItem = { tvSeries ->
                    coroutineScope.expandBottomSheet(bottomSheetScaffoldState)
                    onEvent(DetailScreenEvent.OnClickRecommendationTvSeries(tvSeries))
                }
            )
        }
    )

}

@Composable
private fun DetailScreenContent(
    modifier: Modifier = Modifier,
    uiState: DetailScreenUiState,
    onBackPressed: () -> Unit,
    onNavigateToPersonDetail: (Int) -> Unit,
    onClickRetry: () -> Unit,
    onClickedMovieRecommendationItem: (Movie) -> Unit,
    onClickedTvRecommendationItem: (TvSeries) -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when (uiState) {
            is DetailScreenUiState.Error -> {
                ErrorView(
                    modifier = Modifier.matchParentSize()
                        .align(Alignment.Center),
                    errorMessage = uiState.message,
                    onClickRetry = onClickRetry
                )
            }

            DetailScreenUiState.Loading -> {
                MCircularProgressIndicator()
            }

            is DetailScreenUiState.MovieSuccess -> {
                MovieDetailSuccessView(
                    modifier = modifier.matchParentSize(),
                    movieDetail = uiState.movieDetail,
                    onClickedCastItem = onNavigateToPersonDetail,
                    onClickedDirector = onNavigateToPersonDetail,
                    movieRecommendations = uiState.movieRecommendations.collectAsLazyPagingItems(),
                    onClickedRecommendationItem = onClickedMovieRecommendationItem
                )
            }

            is DetailScreenUiState.TvSeriesSuccess -> {
                TvDetailSuccessView(
                    tvSeriesDetail = uiState.tvSeriesDetail,
                    onClickedCastItem = onNavigateToPersonDetail,
                    onClickedDirector = onNavigateToPersonDetail,
                    tvSeriesRecommendations = uiState.tvSeriesRecommendations.collectAsLazyPagingItems(),
                    onClickedRecommendationItem = onClickedTvRecommendationItem
                )
            }
        }
        BackButton(
            modifier = Modifier
                .padding(MaterialTheme.dimensions.fourLevel)
                .align(alignment = Alignment.TopStart),
            onClick = onBackPressed
        )
    }
}