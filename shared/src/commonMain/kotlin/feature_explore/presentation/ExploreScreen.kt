package feature_explore.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import core.presentation.components.InfoBottomSheet
import core.presentation.components.paging.MPagingVerticalGrid
import core.presentation.theme.dimensions
import feature_explore.domain.multiSearch.MultiSearch
import feature_explore.presentation.components.ExploreScreenTopSectionWithSearchBar
import feature_explore.presentation.components.ExploreSheetContent
import feature_explore.presentation.components.SearchItem
import feature_explore.presentation.components.SearchPersonItem
import feature_explore.presentation.model.SearchItemType
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    uiState: ExploreScreenUiState,
    multiSearchPagingData: LazyPagingItems<MultiSearch>,
    onEvent: (ExploreScreenEvent) -> Unit,
    onNavigateToPersonDetail: (Int) -> Unit,
    onNavigateToDetail: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            skipHiddenState = false
        )
    )

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            if (uiState.selectedMovie != null || uiState.selectedTvSeries != null) {
                InfoBottomSheet(
                    modifier = Modifier.fillMaxWidth(),
                    selectedMovie = uiState.selectedMovie,
                    selectedTvSeries = uiState.selectedTvSeries,
                    onClickClose = {
                        coroutineScope.launch {
                            bottomSheetScaffoldState.bottomSheetState.hide()
                        }
                    },
                    onClickedDetails = onNavigateToDetail
                )
            } else {
                ExploreSheetContent()
            }
        },
        sheetPeekHeight = 0.dp,
        sheetContainerColor = MaterialTheme.colorScheme.background,
        sheetContentColor = MaterialTheme.colorScheme.onBackground
    ) {
        ExploreScreenContent(
            modifier = Modifier.fillMaxSize(),
            uiState = uiState,
            onEvent = onEvent,
            multiSearchPagingData = multiSearchPagingData,
            onClickedFilter = {
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                }
            },
            onNavigateToPersonDetail = onNavigateToPersonDetail,
            onClickedItem = {
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                }
            }
        )
    }
}

@Composable
private fun ExploreScreenContent(
    modifier: Modifier = Modifier,
    uiState: ExploreScreenUiState,
    onEvent: (ExploreScreenEvent) -> Unit,
    multiSearchPagingData: LazyPagingItems<MultiSearch>,
    onClickedFilter: () -> Unit,
    onClickedItem: () -> Unit,
    onNavigateToPersonDetail: (Int) -> Unit
) {
    Box(modifier = modifier) {
        MPagingVerticalGrid(
            pagingItems = multiSearchPagingData,
            isShowAppendLoading = false,
            columns = GridCells.Adaptive(170.dp),
            contentPadding = PaddingValues(MaterialTheme.dimensions.fourLevel),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.fourLevel),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.fourLevel),
            addItemOnTop = {
                item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                    ExploreScreenTopSectionWithSearchBar(
                        modifier = Modifier.fillMaxWidth(),
                        searchText = uiState.searchText,
                        onClickedFilter = {
                            onEvent(ExploreScreenEvent.OnClickFilterItem)
                            onClickedFilter()
                        },
                        onEvent = onEvent
                    )
                }
            },
        ) { multiSearch ->
            when (multiSearch) {
                is MultiSearch.MovieItem -> {
                    SearchItem(
                        modifier = Modifier.clickable {
                            onEvent(ExploreScreenEvent.OnMovieItemClicked(multiSearch.movie))
                            onClickedItem()
                        },
                        title = multiSearch.movie.title,
                        voteAverage = multiSearch.movie.voteAverage,
                        formattedVoteCount = multiSearch.movie.formattedVoteCount,
                        posterImageUrl = multiSearch.movie.posterPath,
                        searchItemType = SearchItemType.MOVIE
                    )
                }

                is MultiSearch.PersonItem -> {
                    SearchPersonItem(
                        modifier = Modifier.clickable {
                            onNavigateToPersonDetail(multiSearch.person.id)
                        },
                        personSearch = multiSearch.person
                    )
                }

                is MultiSearch.TvSeriesItem -> {
                    SearchItem(
                        modifier = Modifier.clickable {
                            onEvent(ExploreScreenEvent.OnTvSeriesItemClicked(multiSearch.tvSeries))
                            onClickedItem()
                        },
                        title = multiSearch.tvSeries.name,
                        voteAverage = multiSearch.tvSeries.voteAverage,
                        formattedVoteCount = multiSearch.tvSeries.formattedVoteCount,
                        posterImageUrl = multiSearch.tvSeries.posterPath,
                        searchItemType = SearchItemType.TV_SERIES
                    )
                }
            }
        }
    }
}