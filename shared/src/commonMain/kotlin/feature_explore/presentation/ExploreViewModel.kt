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
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExploreViewModel(
    private val multiSearchRepository: MultiSearchRepository,
    private val tvGenreRepository: TvGenreRepository,
    private val movieGenreRepository: MovieGenreRepository,
    private val exploreMovieRepository: ExploreMovieRepository,
    private val exploreTvSeriesRepository: ExploreTvSeriesRepository
) : BaseViewModel<ExploreScreenUiState, ExploreScreenEvent>(
    ExploreScreenUiState()
) {
    private var searchJob: Job? = null

    init {
        updateGenres()
    }

    override fun onEvent(event: ExploreScreenEvent) {
        when (event) {
            is ExploreScreenEvent.OnSearchTextChanged -> handleSearchTextChanged(event.searchText)
            is ExploreScreenEvent.OnMovieItemClicked -> {
                mutableState.update {
                    it.copy(
                        selectedMovie = event.movie,
                        selectedTvSeries = null,
                    )
                }
            }

            is ExploreScreenEvent.OnTvSeriesItemClicked -> {
                mutableState.update {
                    it.copy(
                        selectedMovie = null,
                        selectedTvSeries = event.tvSeries,
                    )
                }
            }

            ExploreScreenEvent.OnClickFilterItem -> {
                mutableState.update {
                    it.copy(
                        selectedMovie = null,
                        selectedTvSeries = null,
                    )
                }
            }

            is ExploreScreenEvent.OnClickCategoriesItem -> {
                mutableState.update {
                    it.copy(
                        categoriesFilterItems = it.categoriesFilterItems.mapIsSelected(event.filterItem)
                    )
                }

                updateGenres()
            }

            is ExploreScreenEvent.OnClickSortByItem -> {
                mutableState.update {
                    it.copy(
                        sortByFilterItems = it.sortByFilterItems.mapIsSelected(event.filterItem)
                    )
                }
            }

            is ExploreScreenEvent.OnClickGenreItem -> {

                val newGenreList = state.value.genreFilterItems.map { filterItem ->
                    if (filterItem.title == event.filterItem.title) {
                        filterItem.copy(isSelected = !event.filterItem.isSelected)
                    } else {
                        filterItem
                    }
                }

                mutableState.update {
                    it.copy(
                        genreFilterItems = newGenreList
                    )
                }
            }

            ExploreScreenEvent.OnClickFilterApply -> handleOnClickFilterApply()

            ExploreScreenEvent.OnClickResetButton -> handleOnClickResetButton()
        }
    }

    private fun handleOnClickFilterApply() {
        val selectedCategory = state.value.selectedCategory()
        val selectedSortBy = state.value.selectedSortBy()
        val selectedGenreList = state.value.genreFilterItems.filter { it.isSelected }

        when (selectedCategory) {
            Category.MOVIES -> {
                viewModelScope.launch {
                    val response = exploreMovieRepository.discoverMovie(
                        withGenres = selectedGenreList.joinWithComma(),
                        sortBy = selectedSortBy.queryParam
                    )

                    mutableState.update {
                        it.copy(
                            searchedMovieFlowPagingData = response,
                            multiSearchFlowPagingData = flowOf()
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

                    mutableState.update {
                        it.copy(
                            searchedTvSeriesFlowPagingData = response,
                            multiSearchFlowPagingData = flowOf()
                        )
                    }
                }
            }
        }
    }

    private fun handleSearchTextChanged(searchText: String) {
        mutableState.update { it.copy(searchText = searchText) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            val response = multiSearchRepository.multiSearch(searchText)
            mutableState.update {
                it.copy(
                    multiSearchFlowPagingData = response
                )
            }
        }
    }

    private fun handleOnClickResetButton() {
        mutableState.update {
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
            val genres = when (state.value.selectedCategory()) {
                Category.MOVIES -> {
                    movieGenreRepository.getMovieGenreList()
                }

                Category.TV_SERIES -> {
                    tvGenreRepository.getTvGenreList()
                }
            }

            mutableState.update {
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
