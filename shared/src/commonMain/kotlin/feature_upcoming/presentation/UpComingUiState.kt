package feature_upcoming.presentation

import core.domain.movie.Movie

data class UpComingUiState(
    val selectedMovie: Movie? = null,
)
