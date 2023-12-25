package feature_home.presentation

import core.domain.model.movie.Movie
import core.domain.model.tv.TvSeries

data class HomeScreenUiState(
    val selectedMovie: Movie? = null,
    val selectedTvSeries: TvSeries? = null
)
