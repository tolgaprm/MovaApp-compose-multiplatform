package feature_detail.data.service

import core.common.dispatcher.DispatcherProvider
import core.data.addLanguageParameter
import core.data.util.tryResult
import feature_detail.data.movie.remote.dto.MovieDetailDto
import feature_detail.data.tv.remote.dto.TvSeriesDetailDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
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
                }
            }
        }
    }

    override suspend fun getTvSeriesDetail(id: Int, language: String): TvSeriesDetailDto {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get("$TV_DETAIL/$id") {
                    addLanguageParameter(language = language)
                }
            }
        }
    }

    companion object {
        private const val MOVIE_DETAIL = "movie"
        private const val TV_DETAIL = "tv"
    }
}