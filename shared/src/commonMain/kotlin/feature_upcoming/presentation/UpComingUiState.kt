package feature_upcoming.presentation

import core.domain.model.movie.Movie

data class UpComingUiState(
    val selectedMovie: Movie? = null,
)
