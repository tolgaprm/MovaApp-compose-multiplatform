package feature_person_detail.presentation

sealed interface PersonDetailEvent {

    data class GetPersonDetail(
        val personId: Int
    ) : PersonDetailEvent

    data object OnTryAgainClicked : PersonDetailEvent
}