package feature_home.presentation

import core.domain.movie.Movie
import core.domain.tvseries.TvSeries

data class HomeScreenUiState(
    val selectedMovie: Movie? = null,
    val selectedTvSeries: TvSeries? = null
)
