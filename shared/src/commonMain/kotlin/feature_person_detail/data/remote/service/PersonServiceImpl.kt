package feature_person_detail.data.remote.service

import core.common.dispatcher.DispatcherProvider
import core.data.util.addAppendToResponseQuery
import core.data.util.addLanguageParameter
import core.data.util.tryResult
import feature_person_detail.data.remote.dto.PersonDetailDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.withContext

class PersonServiceImpl(
    private val httpClient: HttpClient,
    private val dispatcherProvider: DispatcherProvider
) : PersonService {
    override suspend fun getPersonDetail(
        id: Int,
        language: String
    ): PersonDetailDto {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get("$PERSON_DETAIL/$id") {
                    addLanguageParameter(language)
                    addAppendToResponseQuery(
                        appendToResponses = listOf(CREDIT_APPEND_TO_RESPONSE)
                    )
                }
            }
        }
    }

    companion object {
        private const val PERSON_DETAIL = "person"
        private const val CREDIT_APPEND_TO_RESPONSE = "combined_credits"
    }
}