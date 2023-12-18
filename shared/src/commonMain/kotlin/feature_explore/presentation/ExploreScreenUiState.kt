package feature_explore.presentation

import androidx.paging.PagingData
import core.domain.movie.Movie
import core.domain.tvseries.TvSeries
import feature_explore.domain.multiSearch.MultiSearch
import feature_explore.presentation.model.Category
import feature_explore.presentation.model.FilterItem
import feature_explore.presentation.model.SortBy
import feature_explore.presentation.model.toFilterItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class ExploreScreenUiState(
    val searchText: String = "",
    val categoriesFilterItems: List<FilterItem> = listOf(
        Category.MOVIES.toFilterItem(isSelected = true),
        Category.TV_SERIES.toFilterItem(isSelected = false)
    ),
    val sortByFilterItems: List<FilterItem> = listOf(
        SortBy.POPULARITY.toFilterItem(isSelected = true),
        SortBy.LATEST_RELEASE.toFilterItem(isSelected = false)
    ),
    val genreFilterItems: List<FilterItem> = emptyList(),
    val multiSearchFlowPagingData: Flow<PagingData<MultiSearch>> = flowOf(),
    val searchedMovieFlowPagingData: Flow<PagingData<Movie>> = flowOf(),
    val searchedTvSeriesFlowPagingData: Flow<PagingData<TvSeries>> = flowOf(),
    val selectedMovie: Movie? = null,
    val selectedTvSeries: TvSeries? = null
)


fun ExploreScreenUiState.selectedCategory(): Category {
    val filterItem = categoriesFilterItems.first { it.isSelected }
    return Category.values()[filterItem.id]
}

fun ExploreScreenUiState.selectedSortBy(): SortBy {
    val filterItem = sortByFilterItems.first { it.isSelected }
    return SortBy.values()[filterItem.id]
}