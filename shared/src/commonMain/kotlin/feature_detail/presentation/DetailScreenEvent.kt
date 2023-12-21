package feature_detail.presentation

import core.domain.movie.Movie
import core.domain.tvseries.TvSeries

sealed interface DetailScreenEvent {
    data class GetMovieDetail(val id: Int?) : DetailScreenEvent

    data class GetTvSeriesDetail(val id: Int?) : DetailScreenEvent

    data object Retry : DetailScreenEvent

    data class OnClickRecommendationMovie(val movie: Movie) : DetailScreenEvent

    data class OnClickRecommendationTvSeries(val tvSeries: TvSeries) : DetailScreenEvent
}