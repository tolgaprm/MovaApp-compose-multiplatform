package feature_detail.data.remote.tv

import core.common.dispatcher.DispatcherProvider
import core.data.addLanguageParameter
import core.data.util.tryResult
import feature_detail.data.remote.tv.dto.TvSeriesDetailDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.withContext

class TvSeriesDetailRemoteDataSource(
    private val httpClient: HttpClient,
    private val dispatcherProvider: DispatcherProvider
) {
    suspend fun getTvSeriesDetail(
        id: Int,
        language: String
    ): TvSeriesDetailDto {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get("$TV_DETAIL/$id") {
                    addLanguageParameter(language = language)
                }
            }
        }
    }

    companion object {
        private const val TV_DETAIL = "tv"
    }
}