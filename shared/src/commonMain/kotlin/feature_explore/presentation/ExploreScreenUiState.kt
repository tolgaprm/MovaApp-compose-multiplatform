package feature_explore.presentation

import androidx.paging.PagingData
import feature_explore.domain.multiSearch.MultiSearch
import feature_explore.presentation.model.FilterItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class ExploreScreenUiState(
    val searchText: String = "",
    val categoriesFilterItems: List<FilterItem> = listOf(
        FilterItem(
            id = 1,
            title = "Movies",
            isSelected = true
        ),
        FilterItem(
            id = 2,
            title = "Tv Series",
            isSelected = false
        )
    ),
    val sortByFilterItems: List<FilterItem> = listOf(
        FilterItem(
            id = 1,
            title = "Popularity",
            isSelected = true
        ),
        FilterItem(
            id = 2,
            title = "Latest Release",
            isSelected = false
        ),
    ),
    val genreFilterItems: List<FilterItem> = emptyList(),
    val multiSearchFlowPagingData: Flow<PagingData<MultiSearch>> = flowOf()
)