package feature_home.presentation

import core.domain.movie.Movie
import core.domain.tvseries.TvSeries

sealed interface HomeScreenEvent {
    data class OnMovieSelected(val movie: Movie) : HomeScreenEvent
    data class OnTvSeriesSelected(val tvSeries: TvSeries) : HomeScreenEvent
}