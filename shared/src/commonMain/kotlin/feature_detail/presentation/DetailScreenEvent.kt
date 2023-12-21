package feature_detail.presentation

sealed interface DetailScreenEvent {
    data class GetMovieDetail(val id: Int?) : DetailScreenEvent

    data class GetTvSeriesDetail(val id: Int?) : DetailScreenEvent

    data object Retry : DetailScreenEvent
}