package feature_detail.data.remote.service

import core.common.dispatcher.DispatcherProvider
import core.data.remote.dto.ApiResponse
import core.data.remote.dto.movie.MovieDto
import core.data.remote.dto.tv.TvSeriesDto
import core.data.util.addCommonParameters
import core.data.util.addLanguageParameter
import core.data.util.tryResult
import feature_detail.data.remote.dto.movie.MovieDetailDto
import feature_detail.data.remote.dto.tv.TvSeriesDetailDto
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext

class DetailServiceImpl(
    private val httpClient: HttpClient,
    private val dispatcherProvider: DispatcherProvider
) : DetailService {
    override suspend fun getMovieDetail(movieId: Int, language: String): MovieDetailDto {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get("$MOVIE_DETAIL/$movieId") {
                    addLanguageParameter(language = language)
                    addAppendToResponseQuery(
                        appendToResponses =
                        listOf(CREDIT_APPEND_TO_RESPONSE, WATCH_PROVIDERS_APPEND_TO_RESPONSE)
                    )
                }
            }
        }
    }

    override suspend fun getTvSeriesDetail(tvSeriesId: Int, language: String): TvSeriesDetailDto {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get("$TV_DETAIL/$tvSeriesId") {
                    addLanguageParameter(language = language)
                    addAppendToResponseQuery(
                        appendToResponses =
                        listOf(CREDIT_APPEND_TO_RESPONSE, WATCH_PROVIDERS_APPEND_TO_RESPONSE)
                    )
                }
            }
        }
    }

    override suspend fun getMovieRecommendations(
        movieId: Int,
        language: String,
        page: Int
    ): ApiResponse<MovieDto> {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get(
                    "$MOVIE_DETAIL/$movieId/$RECOMMENDATIONS",
                ) {
                    addCommonParameters(language = language, page = page)
                }
            }
        }
    }

    override suspend fun getTvSeriesRecommendations(
        tvSeriesId: Int,
        language: String,
        page: Int
    ): ApiResponse<TvSeriesDto> {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get(
                    "$TV_DETAIL/$tvSeriesId/$RECOMMENDATIONS",
                ) {
                    addCommonParameters(language = language, page = page)
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
        private const val RECOMMENDATIONS = "recommendations"
    }

    private fun HttpRequestBuilder.addAppendToResponseQuery(
        appendToResponses: List<String>
    ) {
        parameter(APPEND_TO_RESPONSE_QUERY, appendToResponses.joinToString(","))
    }
}