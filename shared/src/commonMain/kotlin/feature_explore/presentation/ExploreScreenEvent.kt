package feature_explore.presentation

sealed interface ExploreScreenEvent {
    data class OnSearchTextChanged(
        val searchText: String
    ) : ExploreScreenEvent
}