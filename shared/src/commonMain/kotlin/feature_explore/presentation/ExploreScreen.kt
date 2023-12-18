package feature_explore.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import core.domain.movie.Movie
import core.domain.tvseries.TvSeries
import core.presentation.components.InfoBottomSheet
import core.presentation.components.paging.MPagingVerticalGrid
import core.presentation.theme.dimensions
import feature_explore.domain.multiSearch.MultiSearch
import feature_explore.presentation.components.ExploreScreenTopSectionWithSearchBar
import feature_explore.presentation.components.ExploreSheetContent
import feature_explore.presentation.components.SearchItem
import feature_explore.presentation.components.SearchPersonItem
import feature_explore.presentation.model.SearchItemType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    uiState: ExploreScreenUiState,
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
                        coroutineScope.hideBottomSheet(bottomSheetScaffoldState)
                    },
                    onClickedDetails = onNavigateToDetail
                )
            } else {
                ExploreSheetContent(modifier = Modifier.fillMaxWidth(),
                    categoriesFilterItems = uiState.categoriesFilterItems,
                    sortByFilterItems = uiState.sortByFilterItems,
                    genreFilterItems = uiState.genreFilterItems,
                    onClickCategoryItem = {
                        onEvent(ExploreScreenEvent.OnClickCategoriesItem(it))
                    },
                    onClickGenreItem = {
                        onEvent(ExploreScreenEvent.OnClickGenreItem(it))
                    },
                    onClickSortByItem = {
                        onEvent(ExploreScreenEvent.OnClickSortByItem(it))
                    },
                    onClickResetButton = {
                        onEvent(ExploreScreenEvent.OnClickResetButton)

                        coroutineScope.hideBottomSheet(bottomSheetScaffoldState)
                    },
                    onClickFilterApply = {
                        onEvent(ExploreScreenEvent.OnClickFilterApply)

                        coroutineScope.hideBottomSheet(bottomSheetScaffoldState)
                    })
            }
        },
        sheetPeekHeight = 0.dp,
        topBar = {
            ExploreScreenTopSectionWithSearchBar(
                modifier = Modifier.fillMaxWidth(),
                searchText = uiState.searchText,
                onClickedFilter = {
                    if (bottomSheetScaffoldState.bottomSheetState.isVisible) {
                        coroutineScope.hideBottomSheet(bottomSheetScaffoldState)
                    } else {
                        coroutineScope.expandBottomSheet(bottomSheetScaffoldState)
                    }
                    onEvent(ExploreScreenEvent.OnClickFilterItem)
                },
                onSearchQueryChanged = {
                    onEvent(ExploreScreenEvent.OnSearchTextChanged(it))
                }
            )
        },
        sheetContainerColor = MaterialTheme.colorScheme.background,
        sheetContentColor = MaterialTheme.colorScheme.onBackground
    ) {
        when (uiState) {
            is ExploreScreenUiState.MultiSearchResponse -> {
                ExploreScreenMultiSearchContent(
                    modifier = Modifier.fillMaxWidth(),
                    multiSearchPagingData = uiState.multiSearchFlowPagingData.collectAsLazyPagingItems(),
                    onClickMovieItem = {
                        onEvent(ExploreScreenEvent.OnMovieItemClicked(it))
                        coroutineScope.expandBottomSheet(bottomSheetScaffoldState)
                    },
                    onClickTvSeriesItem = {
                        onEvent(ExploreScreenEvent.OnTvSeriesItemClicked(it))
                        coroutineScope.expandBottomSheet(bottomSheetScaffoldState)
                    },
                    onNavigateToPersonDetail = onNavigateToPersonDetail
                )
            }

            is ExploreScreenUiState.SearchedWithFilters -> {
                val moviePagingItems =
                    uiState.searchedMovieFlowPagingData.collectAsLazyPagingItems()
                val tvSeriesPagingItems =
                    uiState.searchedTvSeriesFlowPagingData.collectAsLazyPagingItems()
                if (moviePagingItems.itemCount > 0) {
                    ExploreScreenMovieSearchedWithFiltersContent(
                        moviePagingItems = moviePagingItems,
                        onClickMovieItem = {
                            onEvent(ExploreScreenEvent.OnMovieItemClicked(it))
                            coroutineScope.expandBottomSheet(bottomSheetScaffoldState)
                        }
                    )
                } else {
                    ExploreScreenTvSeriesSearchedWithFiltersContent(
                        moviePagingItems = tvSeriesPagingItems,
                        onClickTvSeriesItem = {
                            onEvent(ExploreScreenEvent.OnTvSeriesItemClicked(it))
                            coroutineScope.expandBottomSheet(bottomSheetScaffoldState)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ExploreScreenMultiSearchContent(
    modifier: Modifier = Modifier,
    multiSearchPagingData: LazyPagingItems<MultiSearch>,
    onClickMovieItem: (Movie) -> Unit,
    onClickTvSeriesItem: (TvSeries) -> Unit,
    onNavigateToPersonDetail: (Int) -> Unit
) {
    ExplorePagingVerticalGrid(
        modifier = modifier,
        pagingItems = multiSearchPagingData,
    ) { multiSearch ->
        when (multiSearch) {
            is MultiSearch.MovieItem -> {
                SearchItem(
                    modifier = Modifier.clickable {
                        onClickMovieItem(multiSearch.movie)
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
                    }, personSearch = multiSearch.person
                )
            }

            is MultiSearch.TvSeriesItem -> {
                SearchItem(
                    modifier = Modifier.clickable {
                        onClickTvSeriesItem(multiSearch.tvSeries)
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

@Composable
private fun ExploreScreenMovieSearchedWithFiltersContent(
    modifier: Modifier = Modifier,
    moviePagingItems: LazyPagingItems<Movie>,
    onClickMovieItem: (Movie) -> Unit
) {
    ExplorePagingVerticalGrid(
        modifier = modifier,
        pagingItems = moviePagingItems
    ) { movie ->
        SearchItem(
            modifier = Modifier.clickable {
                onClickMovieItem(movie)
            },
            title = movie.title,
            voteAverage = movie.voteAverage,
            formattedVoteCount = movie.formattedVoteCount,
            posterImageUrl = movie.posterPath,
            searchItemType = SearchItemType.MOVIE
        )
    }
}

@Composable
private fun ExploreScreenTvSeriesSearchedWithFiltersContent(
    modifier: Modifier = Modifier,
    moviePagingItems: LazyPagingItems<TvSeries>,
    onClickTvSeriesItem: (TvSeries) -> Unit
) {
    ExplorePagingVerticalGrid(
        modifier = modifier,
        pagingItems = moviePagingItems
    ) { tvSeries ->
        SearchItem(
            modifier = Modifier.clickable {
                onClickTvSeriesItem(tvSeries)
            },
            title = tvSeries.name,
            voteAverage = tvSeries.voteAverage,
            formattedVoteCount = tvSeries.formattedVoteCount,
            posterImageUrl = tvSeries.posterPath,
            searchItemType = SearchItemType.TV_SERIES
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private fun CoroutineScope.expandBottomSheet(bottomSheetScaffoldState: BottomSheetScaffoldState) {
    launch {
        bottomSheetScaffoldState.bottomSheetState.expand()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private fun CoroutineScope.hideBottomSheet(bottomSheetScaffoldState: BottomSheetScaffoldState) {
    launch {
        bottomSheetScaffoldState.bottomSheetState.hide()
    }
}

@Composable
private fun <T : Any> ExplorePagingVerticalGrid(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<T>,
    itemContent: @Composable (T) -> Unit
) {
    MPagingVerticalGrid(
        modifier = modifier,
        pagingItems = pagingItems,
        isShowAppendLoading = false,
        columns = GridCells.Adaptive(170.dp),
        contentPadding = PaddingValues(MaterialTheme.dimensions.fourLevel),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.fourLevel),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.fourLevel)
    ) {
        itemContent(it)
    }
}