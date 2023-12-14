package feature_explore.presentation

import core.presentation.base.BaseViewModel
import core.presentation.util.viewModelScope
import feature_explore.domain.multiSearch.MultiSearchRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExploreViewModel(
    private val multiSearchRepository: MultiSearchRepository
) : BaseViewModel<ExploreScreenUiState, ExploreScreenEvent>(
    ExploreScreenUiState()
) {
    private var searchJob: Job? = null

    override fun onEvent(event: ExploreScreenEvent) {
        when (event) {
            is ExploreScreenEvent.OnSearchTextChanged -> handleSearchTextChanged(event.searchText)
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
}
