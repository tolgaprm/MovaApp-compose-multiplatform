package feature_detail.data.service

import core.common.dispatcher.DispatcherProvider
import core.data.addLanguageParameter
import core.data.util.tryResult
import feature_detail.data.movie.remote.dto.MovieDetailDto
import feature_detail.data.tv.remote.dto.TvSeriesDetailDto
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext

class DetailServiceImpl(
    private val httpClient: HttpClient,
    private val dispatcherProvider: DispatcherProvider
) : DetailService {
    override suspend fun getMovieDetail(id: Int, language: String): MovieDetailDto {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get("$MOVIE_DETAIL/$id") {
                    addLanguageParameter(language = language)
                    addAppendToResponseQuery(
                        appendToResponses =
                        listOf(CREDIT_APPEND_TO_RESPONSE, WATCH_PROVIDERS_APPEND_TO_RESPONSE)
                    )
                }
            }
        }
    }

    override suspend fun getTvSeriesDetail(id: Int, language: String): TvSeriesDetailDto {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get("$TV_DETAIL/$id") {
                    addLanguageParameter(language = language)
                    addAppendToResponseQuery(
                        appendToResponses =
                        listOf(CREDIT_APPEND_TO_RESPONSE, WATCH_PROVIDERS_APPEND_TO_RESPONSE)
                    )
                }
            }
        }
    }

    companion object {
        private const val MOVIE_DETAIL = "movie"
        private const val TV_DETAIL = "tv"
        private const val APPEND_TO_RESPONSE_QUERY = "append_to_response"
        private const val CREDIT_APPEND_TO_RESPONSE = "credits"
        private const val WATCH_PROVIDERS_APPEND_TO_RESPONSE = "watch/providers"
    }

    private fun HttpRequestBuilder.addAppendToResponseQuery(
        appendToResponses: List<String>
    ) {
        parameter(APPEND_TO_RESPONSE_QUERY, appendToResponses.joinToString(","))
    }
}