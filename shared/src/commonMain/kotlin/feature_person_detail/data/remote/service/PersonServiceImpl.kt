package feature_person_detail.data.remote.service

import core.common.dispatcher.DispatcherProvider
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
                }
            }
        }
    }

    companion object {
        private const val PERSON_DETAIL = "person"
    }
}