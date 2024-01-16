package feature_person_detail.domain.usecase

import core.common.Resource
import core.common.dispatcher.DispatcherProvider
import core.domain.error.UnknownErrorException
import feature_person_detail.domain.model.PersonDetail
import feature_person_detail.domain.repository.PersonRepository
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

class GetPersonDetailUseCase(
    private val personRepository: PersonRepository,
    private val dispatcherProvider: DispatcherProvider
) : KoinComponent {

    suspend operator fun invoke(
        personId: Int,
        language: String = "en"
    ): Resource<PersonDetail> {
        val personDetailResponse = personRepository.getPersonDetail(
            id = personId,
            language = language
        )

        return when (personDetailResponse) {
            is Resource.Success -> {
                withContext(dispatcherProvider.Default) {
                    Resource.Success(
                        sortByPopularity(
                            personDetailResponse.data ?: return@withContext
                            Resource.Error(
                                UnknownErrorException()
                            )
                        )
                    )
                }
            }

            is Resource.Error -> {
                return Resource.Error(
                    personDetailResponse.error ?: UnknownErrorException()
                )
            }
        }
    }
}


private fun sortByPopularity(personDetail: PersonDetail): PersonDetail {

    return personDetail.copy(
        combinedCredit = personDetail.combinedCredit?.copy(
            cast = personDetail.combinedCredit.cast.sortedByDescending { it.popularity },
            crew = personDetail.combinedCredit.crew.sortedByDescending { it.popularity }
        )
    )
}