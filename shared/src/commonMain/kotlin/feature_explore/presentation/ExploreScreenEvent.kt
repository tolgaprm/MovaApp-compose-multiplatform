package feature_explore.presentation

import core.domain.movie.Movie
import core.domain.tvseries.TvSeries
import feature_explore.presentation.model.FilterItem

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

    data class OnClickCategoriesItem(
        val filterItem: FilterItem
    ) : ExploreScreenEvent

    data class OnClickSortByItem(
        val filterItem: FilterItem
    ) : ExploreScreenEvent

    data class OnClickGenreItem(
        val filterItem: FilterItem
    ) : ExploreScreenEvent

    data object OnClickResetButton : ExploreScreenEvent

    data object OnClickFilterApply : ExploreScreenEvent
}