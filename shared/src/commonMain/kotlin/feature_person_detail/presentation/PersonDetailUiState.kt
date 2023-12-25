package feature_person_detail.presentation

import feature_person_detail.domain.model.PersonDetail

sealed interface PersonDetailUiState {
    data object Loading : PersonDetailUiState
    data class Success(
        val personDetail: PersonDetail
    ) : PersonDetailUiState

    data class Error(
        val message: String
    ) : PersonDetailUiState
}