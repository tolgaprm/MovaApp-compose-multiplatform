package feature_explore.presentation

import androidx.paging.PagingData
import core.domain.model.movie.Movie
import core.domain.model.tv.TvSeries
import feature_explore.domain.model.MultiSearch
import feature_explore.presentation.model.Category
import feature_explore.presentation.model.FilterItem
import feature_explore.presentation.model.SortBy
import feature_explore.presentation.model.toFilterItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

sealed class ExploreScreenUiState {
    open val selectedMovie: Movie? = null
    open val selectedTvSeries: TvSeries? = null
    open val genreFilterItems: List<FilterItem> = emptyList()
    open val categoriesFilterItems: List<FilterItem> = defaultCategoriesFilterItems
    open val sortByFilterItems: List<FilterItem> = defaultSortByFilterItems

    data class MultiSearchResponse(
        val multiSearchFlowPagingData: Flow<PagingData<MultiSearch>> = flowOf(),
        override val selectedMovie: Movie? = null,
        override val selectedTvSeries: TvSeries? = null
    ) : ExploreScreenUiState()

    data class SearchedWithFilters(
        val searchedMovieFlowPagingData: Flow<PagingData<Movie>> = flowOf(),
        val searchedTvSeriesFlowPagingData: Flow<PagingData<TvSeries>> = flowOf(),
        override val genreFilterItems: List<FilterItem> = emptyList(),
        override val categoriesFilterItems: List<FilterItem> = emptyList(),
        override val sortByFilterItems: List<FilterItem> = emptyList(),
        override val selectedMovie: Movie? = null,
        override val selectedTvSeries: TvSeries? = null
    ) : ExploreScreenUiState()
}

data class ExploreViewModelState(
    val categoriesFilterItems: List<FilterItem> = defaultCategoriesFilterItems,
    val sortByFilterItems: List<FilterItem> = defaultSortByFilterItems,
    val genreFilterItems: List<FilterItem> = emptyList(),
    val multiSearchFlowPagingData: Flow<PagingData<MultiSearch>> = flowOf(),
    val searchedMovieFlowPagingData: Flow<PagingData<Movie>> = flowOf(),
    val searchedTvSeriesFlowPagingData: Flow<PagingData<TvSeries>> = flowOf(),
    val selectedMovie: Movie? = null,
    val selectedTvSeries: TvSeries? = null,
    val isActiveFilter: Boolean = false
) {
    fun toUiState(): ExploreScreenUiState {
        return if (isActiveFilter) {
            ExploreScreenUiState.SearchedWithFilters(
                searchedMovieFlowPagingData = searchedMovieFlowPagingData,
                searchedTvSeriesFlowPagingData = searchedTvSeriesFlowPagingData,
                genreFilterItems = genreFilterItems,
                categoriesFilterItems = categoriesFilterItems,
                sortByFilterItems = sortByFilterItems,
                selectedMovie = selectedMovie,
                selectedTvSeries = selectedTvSeries
            )
        } else {
            ExploreScreenUiState.MultiSearchResponse(
                multiSearchFlowPagingData = multiSearchFlowPagingData,
                selectedMovie = selectedMovie,
                selectedTvSeries = selectedTvSeries
            )
        }
    }
}

fun ExploreViewModelState.selectedCategory(): Category {
    val filterItem = categoriesFilterItems.first { it.isSelected }
    return Category.entries[filterItem.id]
}

fun ExploreViewModelState.selectedSortBy(): SortBy {
    val filterItem = sortByFilterItems.first { it.isSelected }
    return SortBy.entries[filterItem.id]
}

private val defaultCategoriesFilterItems = listOf(
    Category.MOVIES.toFilterItem(isSelected = true),
    Category.TV_SERIES.toFilterItem(isSelected = false)
)

private val defaultSortByFilterItems = listOf(
    SortBy.POPULARITY.toFilterItem(isSelected = true),
    SortBy.LATEST_RELEASE.toFilterItem(isSelected = false)
)
