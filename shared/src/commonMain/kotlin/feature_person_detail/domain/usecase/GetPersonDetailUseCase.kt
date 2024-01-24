package feature_person_detail.domain.usecase

import core.common.Resource
import core.common.dispatcher.DispatcherProvider
import core.domain.error.UnknownErrorException
import feature_person_detail.domain.model.PersonDetail
import feature_person_detail.domain.repository.PersonRepository
import kotlinx.coroutines.withContext

class GetPersonDetailUseCase(
    private val personRepository: PersonRepository,
    private val dispatcherProvider: DispatcherProvider
) {

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
                if (personDetailResponse.data == null) return Resource.Error(UnknownErrorException())
                Resource.Success(
                    sortByPopularity(
                        personDetailResponse.data,
                        dispatcherProvider
                    )
                )
            }

            is Resource.Error -> {
                return Resource.Error(
                    personDetailResponse.error ?: UnknownErrorException()
                )
            }
        }
    }
}


private suspend fun sortByPopularity(
    personDetail: PersonDetail,
    dispatcherProvider: DispatcherProvider
): PersonDetail {

    return withContext(dispatcherProvider.Default) {
        personDetail.copy(
            combinedCredit = personDetail.combinedCredit?.copy(
                cast = personDetail.combinedCredit.cast.distinctBy { it.id }
                    .sortedByDescending { it.popularity },

                crew = personDetail.combinedCredit.crew.distinctBy { it.id }
                    .sortedByDescending { it.popularity }

            )
        )
    }
}