package feature_person_detail.presentation

import core.presentation.base.BaseViewModel
import core.presentation.util.viewModelScope
import feature_person_detail.domain.usecase.GetPersonDetailUseCase
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PersonDetailViewModel(
    private val getPersonDetailUseCase: GetPersonDetailUseCase
) :
    BaseViewModel<PersonDetailUiState, PersonDetailEvent>(PersonDetailUiState.Loading) {
    private var personId: Int = 0

    override fun onEvent(event: PersonDetailEvent) {
        when (event) {
            is PersonDetailEvent.GetPersonDetail -> {
                personId = event.personId
                getPersonDetail(event.personId)
            }

            is PersonDetailEvent.OnTryAgainClicked -> getPersonDetail(personId = personId)
        }
    }

    private fun getPersonDetail(personId: Int) {
        viewModelScope.launch {
            mutableState.update { PersonDetailUiState.Loading }
            handleResourceWithCallbacks(
                resourceSupplier = {
                    getPersonDetailUseCase(personId = personId)
                },
                onSuccessCallback = { personDetail ->
                    mutableState.update {
                        PersonDetailUiState.Success(
                            personDetail = personDetail
                        )
                    }
                },
                onErrorCallback = { error ->
                    mutableState.update { PersonDetailUiState.Error(message = error) }
                }
            )
        }
    }
}