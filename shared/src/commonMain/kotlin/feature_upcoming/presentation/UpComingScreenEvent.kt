package feature_upcoming.presentation

import core.domain.movie.Movie

sealed class UpComingScreenEvent {
    data class OnClickInfo(val movie: Movie) : UpComingScreenEvent()
}