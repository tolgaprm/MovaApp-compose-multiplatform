package feature_home.presentation

import core.domain.model.movie.Movie
import core.domain.model.tv.TvSeries

sealed interface HomeScreenEvent {
    data class OnMovieSelected(val movie: Movie) : HomeScreenEvent
    data class OnTvSeriesSelected(val tvSeries: TvSeries) : HomeScreenEvent
}