package feature_explore.presentation

import core.domain.movie.Movie
import core.domain.tvseries.TvSeries

sealed interface ExploreScreenEvent {
    data class OnSearchTextChanged(
        val searchText: String
    ) : ExploreScreenEvent

    data class OnMovieItemClicked(
        val movie: Movie
    ) : ExploreScreenEvent

    data class OnTvSeriesItemClicked(
        val tvSeries: TvSeries
    ) : ExploreScreenEvent

    data object OnClickFilterItem : ExploreScreenEvent
}