package feature_explore.presentation

sealed interface ExploreScreenEvent {
    data class OnSearchTextChanged(
        val text: String
    ) : ExploreScreenEvent
}