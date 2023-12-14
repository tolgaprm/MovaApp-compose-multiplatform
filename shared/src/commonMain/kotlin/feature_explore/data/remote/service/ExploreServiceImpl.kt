package feature_explore.data.remote.service

import core.common.dispatcher.DispatcherProvider
import core.data.addCommonParameters
import core.data.dto.ApiResponse
import core.data.util.tryResult
import feature_explore.data.multiSearch.dto.SearchDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext

class ExploreServiceImpl(
    private val httpClient: HttpClient,
    private val dispatcherProvider: DispatcherProvider
) : ExploreService {
    override suspend fun multiSearch(
        query: String,
        language: String,
        page: Int
    ): ApiResponse<SearchDto> {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get(MULTI_SEARCH) {
                    addCommonParameters(language = language, page = page)
                    parameter(QUERY_PARAM, query)
                }
            }
        }
    }

    companion object {
        const val MULTI_SEARCH = "search/multi"
        const val QUERY_PARAM = "query"
    }
}