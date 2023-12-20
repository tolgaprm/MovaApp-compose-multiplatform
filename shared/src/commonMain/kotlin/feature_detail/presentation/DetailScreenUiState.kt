package feature_detail.presentation

import feature_detail.domain.movie.model.MovieDetail
import feature_detail.domain.tv.model.TvSeriesDetail

sealed interface DetailScreenUiState {
    data object Loading : DetailScreenUiState

    data class MovieSuccess(val movieDetail: MovieDetail) : DetailScreenUiState

    data class TvSeriesSuccess(val tvSeriesDetail: TvSeriesDetail) : DetailScreenUiState

    data class Error(
        val message: String,
        val tvSeriesId: Int? = null,
        val movieId: Int? = null
    ) : DetailScreenUiState
}
