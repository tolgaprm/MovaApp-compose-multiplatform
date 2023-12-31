package feature_detail.presentation

import androidx.paging.PagingData
import core.domain.model.movie.Movie
import core.domain.model.tv.TvSeries
import feature_detail.domain.model.movie.MovieDetail
import feature_detail.domain.model.tv.TvSeriesDetail
import kotlinx.coroutines.flow.Flow

sealed interface DetailScreenUiState {
    data object Loading : DetailScreenUiState

    data class MovieSuccess(
        val movieDetail: MovieDetail,
        val movieRecommendations: Flow<PagingData<Movie>>,
        val selectedRecommendationMovie: Movie? = null
    ) : DetailScreenUiState

    data class TvSeriesSuccess(
        val tvSeriesDetail: TvSeriesDetail,
        val tvSeriesRecommendations: Flow<PagingData<TvSeries>>,
        val selectedRecommendationTvSeries: TvSeries? = null
    ) : DetailScreenUiState

    data class Error(
        val message: String,
        val tvSeriesId: Int? = null,
        val movieId: Int? = null
    ) : DetailScreenUiState
}

fun DetailScreenUiState.getMovieSuccess(): DetailScreenUiState.MovieSuccess? =
    (this as? DetailScreenUiState.MovieSuccess)

fun DetailScreenUiState.getTvSeriesSuccess(): DetailScreenUiState.TvSeriesSuccess? =
    (this as? DetailScreenUiState.TvSeriesSuccess)
