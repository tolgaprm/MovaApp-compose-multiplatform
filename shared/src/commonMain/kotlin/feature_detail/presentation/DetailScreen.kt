package feature_detail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import core.presentation.components.BackButton
import core.presentation.components.ErrorView
import core.presentation.components.MCircularProgressIndicator
import core.presentation.theme.dimensions
import feature_detail.presentation.components.MovieDetailSuccessView
import feature_detail.presentation.components.TvDetailSuccessView

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    uiState: DetailScreenUiState,
    onBackPressed: () -> Unit,
    onNavigateToPersonDetail: (Int) -> Unit,
    onEvent: (DetailScreenEvent) -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            when (uiState) {
                is DetailScreenUiState.Error -> {
                    ErrorView(
                        modifier = Modifier.matchParentSize()
                            .align(Alignment.Center),
                        errorMessage = uiState.message,
                        onClickRetry = {
                            onEvent(DetailScreenEvent.Retry)
                        }
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
                        onClickedDirector = onNavigateToPersonDetail
                    )
                }

                is DetailScreenUiState.TvSeriesSuccess -> {
                    TvDetailSuccessView(
                        tvSeriesDetail = uiState.tvSeriesDetail,
                        onClickedCastItem = onNavigateToPersonDetail,
                        onClickedDirector = onNavigateToPersonDetail
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
}