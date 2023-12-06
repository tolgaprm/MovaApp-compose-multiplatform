package feature_home.data.tv

import core.common.dispatcher.DispatcherProvider
import core.data.addCommonParameters
import core.data.dto.ApiResponse
import core.data.tvseries.TvSeriesDto
import core.data.util.tryResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.withContext

class TvSeriesRemoteDataSource(
    private val httpClient: HttpClient,
    private val dispatcherProvider: DispatcherProvider
) {
    suspend fun getPopularTvSeries(
        language: String,
        page: Int
    ): ApiResponse<TvSeriesDto> {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get("tv/popular") {
                    addCommonParameters(language = language, page = page)
                }
            }
        }
    }

    suspend fun getTopRatedTvSeries(
        language: String,
        page: Int
    ): ApiResponse<TvSeriesDto> {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get("tv/top_rated") {
                    addCommonParameters(language = language, page = page)
                }
            }
        }
    }
}