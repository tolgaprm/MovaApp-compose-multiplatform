package feature_detail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import core.presentation.components.BackButton
import core.presentation.components.ErrorView
import core.presentation.components.MCircularProgressIndicator
import core.presentation.theme.dimensions
import core.presentation.util.collectAsStateWithLifecycleM
import core.presentation.viewModel
import feature_detail.presentation.components.MovieDetailSuccessView
import feature_detail.presentation.components.TvDetailSuccessView

data class DetailScreenRoute(
    val movieId: Int? = null,
    val tvSeriesId: Int? = null
) : Screen {

    @Composable
    override fun Content() {
        val detailViewModel = viewModel<DetailViewModel>()
        LaunchedEffect(movieId, tvSeriesId) {
            movieId?.let {
                detailViewModel.onEvent(DetailScreenEvent.GetMovieDetail(it))
            }

            tvSeriesId?.let {
                detailViewModel.onEvent(DetailScreenEvent.GetTvSeriesDetail(it))
            }
        }
        val uiState = detailViewModel.state.collectAsStateWithLifecycleM()
        DetailScreen(uiState = uiState)
    }
}

@Composable
private fun DetailScreen(
    modifier: Modifier = Modifier,
    uiState: DetailScreenUiState
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(it)
        ) {
            when (uiState) {
                is DetailScreenUiState.Error -> {
                    ErrorView(
                        modifier = Modifier.matchParentSize()
                            .align(Alignment.Center),
                        errorMessage = uiState.message,
                        onClickRetry = {
                            // TODO
                        }
                    )
                }

                DetailScreenUiState.Loading -> {
                    MCircularProgressIndicator()
                }

                is DetailScreenUiState.MovieSuccess -> {
                    MovieDetailSuccessView(
                        modifier = modifier.matchParentSize(),
                        movieDetail = uiState.movieDetail
                    )
                }

                is DetailScreenUiState.TvSeriesSuccess -> {
                    TvDetailSuccessView(
                        tvSeriesDetail = uiState.tvSeriesDetail
                    )
                }
            }
            BackButton(
                modifier = Modifier
                    .padding(MaterialTheme.dimensions.fourLevel)
                    .align(alignment = Alignment.TopStart),
                onClick = { // TODO
                }
            )
        }
    }
}