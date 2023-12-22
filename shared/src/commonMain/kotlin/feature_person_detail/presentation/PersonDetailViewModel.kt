package feature_person_detail.presentation

import core.presentation.base.BaseViewModel
import core.presentation.util.viewModelScope
import feature_person_detail.domain.repository.PersonRepository
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PersonDetailViewModel(
    private val personRepository: PersonRepository
) :
    BaseViewModel<PersonDetailUiState, PersonDetailEvent>(PersonDetailUiState.Loading) {

    override fun onEvent(event: PersonDetailEvent) {
        when (event) {
            is PersonDetailEvent.GetPersonDetail -> getPersonDetail(event.personId)
        }
    }

    private fun getPersonDetail(personId: Int) {
        viewModelScope.launch {
            mutableState.update { PersonDetailUiState.Loading }
            handleResourceWithCallbacks(
                resourceSupplier = {
                    personRepository.getPersonDetail(personId)
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