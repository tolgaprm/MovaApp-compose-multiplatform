package feature_explore.presentation

import core.domain.genre.movie.MovieGenreRepository
import core.domain.genre.tv.TvGenreRepository
import core.presentation.base.BaseViewModel
import core.presentation.util.viewModelScope
import feature_explore.domain.movie.ExploreMovieRepository
import feature_explore.domain.multiSearch.MultiSearchRepository
import feature_explore.domain.tv.ExploreTvSeriesRepository
import feature_explore.presentation.model.Category
import feature_explore.presentation.model.FilterItem
import feature_explore.presentation.model.SortBy
import feature_explore.presentation.model.joinWithComma
import feature_explore.presentation.model.toFilterItem
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExploreViewModel(
    private val multiSearchRepository: MultiSearchRepository,
    private val tvGenreRepository: TvGenreRepository,
    private val movieGenreRepository: MovieGenreRepository,
    private val exploreMovieRepository: ExploreMovieRepository,
    private val exploreTvSeriesRepository: ExploreTvSeriesRepository
) : BaseViewModel<ExploreScreenUiState, ExploreScreenEvent>(
    ExploreScreenUiState.MultiSearchResponse()
) {
    private val viewModelState = MutableStateFlow(ExploreViewModelState())
    private var searchJob: Job? = null

    val uiState = viewModelState.map(ExploreViewModelState::toUiState)
        .stateIn(
            scope = viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        updateGenres()
    }

    override fun onEvent(event: ExploreScreenEvent) {
        when (event) {
            is ExploreScreenEvent.OnSearchTextChanged -> handleSearchTextChanged(event.searchText)
            is ExploreScreenEvent.OnMovieItemClicked -> {
                viewModelState.update {
                    it.copy(
                        selectedMovie = event.movie,
                        selectedTvSeries = null,
                    )
                }
            }

            is ExploreScreenEvent.OnTvSeriesItemClicked -> {
                viewModelState.update {
                    it.copy(
                        selectedMovie = null,
                        selectedTvSeries = event.tvSeries,
                    )
                }
            }

            ExploreScreenEvent.OnClickFilterItem -> {
                viewModelState.update {
                    it.copy(
                        selectedMovie = null,
                        selectedTvSeries = null
                    )
                }
            }

            is ExploreScreenEvent.OnClickCategoriesItem -> {
                viewModelState.update {
                    it.copy(
                        categoriesFilterItems = it.categoriesFilterItems.mapIsSelected(event.filterItem),
                        isActiveFilter = true
                    )
                }

                updateGenres()
            }

            is ExploreScreenEvent.OnClickSortByItem -> {
                viewModelState.update {
                    it.copy(
                        sortByFilterItems = it.sortByFilterItems.mapIsSelected(event.filterItem),
                        isActiveFilter = true
                    )
                }
            }

            is ExploreScreenEvent.OnClickGenreItem -> {

                val newGenreList = viewModelState.value.genreFilterItems.map { filterItem ->
                    if (filterItem.title == event.filterItem.title) {
                        filterItem.copy(isSelected = !event.filterItem.isSelected)
                    } else {
                        filterItem
                    }
                }

                viewModelState.update {
                    it.copy(
                        genreFilterItems = newGenreList,
                        isActiveFilter = true
                    )
                }
            }

            ExploreScreenEvent.OnClickFilterApply -> handleOnClickFilterApply()

            ExploreScreenEvent.OnClickResetButton -> handleOnClickResetButton()
        }
    }

    private fun handleOnClickFilterApply() {
        val selectedCategory = viewModelState.value.selectedCategory()
        val selectedSortBy = viewModelState.value.selectedSortBy()
        val selectedGenreList = state.value.genreFilterItems.filter { it.isSelected }

        when (selectedCategory) {
            Category.MOVIES -> {
                viewModelScope.launch {
                    val response = exploreMovieRepository.discoverMovie(
                        withGenres = selectedGenreList.joinWithComma(),
                        sortBy = selectedSortBy.queryParam
                    )

                    viewModelState.update {
                        it.copy(
                            searchText = "",
                            searchedMovieFlowPagingData = response,
                            multiSearchFlowPagingData = flowOf(),
                            searchedTvSeriesFlowPagingData = flowOf()
                        )
                    }
                }
            }

            Category.TV_SERIES -> {
                viewModelScope.launch {
                    val response = exploreTvSeriesRepository.discoverTvSeries(
                        withGenres = selectedGenreList.joinWithComma(),
                        sortBy = selectedSortBy.queryParam
                    )

                    viewModelState.update {
                        it.copy(
                            searchText = "",
                            searchedTvSeriesFlowPagingData = response,
                            multiSearchFlowPagingData = flowOf(),
                            searchedMovieFlowPagingData = flowOf()
                        )
                    }
                }
            }
        }
    }

    private fun handleSearchTextChanged(searchText: String) {
        viewModelState.update { it.copy(searchText = searchText) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            val response = multiSearchRepository.multiSearch(searchText)
            viewModelState.update {
                it.copy(
                    multiSearchFlowPagingData = response,
                    isActiveFilter = false
                )
            }
        }
    }

    private fun handleOnClickResetButton() {
        viewModelState.update {
            it.copy(
                genreFilterItems = it.genreFilterItems.map { it.copy(isSelected = false) },
                categoriesFilterItems = it.categoriesFilterItems.mapIsSelected(
                    selectedFilterItem = Category.MOVIES.toFilterItem(
                        isSelected = true
                    )
                ),
                sortByFilterItems = it.sortByFilterItems.mapIsSelected(
                    selectedFilterItem = SortBy.POPULARITY.toFilterItem(
                        isSelected = true
                    )
                )
            )
        }
    }

    private fun updateGenres() {
        viewModelScope.launch {
            val genres = when (viewModelState.value.selectedCategory()) {
                Category.MOVIES -> {
                    movieGenreRepository.getMovieGenreList()
                }

                Category.TV_SERIES -> {
                    tvGenreRepository.getTvGenreList()
                }
            }

            viewModelState.update {
                it.copy(
                    genreFilterItems = genres.map { genre ->
                        FilterItem(
                            id = genre.id,
                            title = genre.name,
                            isSelected = false
                        )
                    }
                )
            }
        }
    }

    private fun List<FilterItem>.mapIsSelected(selectedFilterItem: FilterItem): List<FilterItem> {
        return map { filterItem ->
            filterItem.copy(
                isSelected = filterItem.title == selectedFilterItem.title
            )
        }
    }
}
