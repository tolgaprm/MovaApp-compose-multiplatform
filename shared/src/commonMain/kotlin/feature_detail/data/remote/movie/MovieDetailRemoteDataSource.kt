package feature_detail.data.remote.movie

import core.common.dispatcher.DispatcherProvider
import core.data.addLanguageParameter
import core.data.util.tryResult
import feature_detail.data.remote.movie.dto.MovieDetailDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.withContext

class MovieDetailRemoteDataSource(
    private val httpClient: HttpClient,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend fun getMovieDetail(
        id: Int,
        language: String
    ): MovieDetailDto {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get("$MOVIE_DETAIL/$id") {
                    addLanguageParameter(language = language)
                }
            }
        }
    }

    companion object {
        private const val MOVIE_DETAIL = "movie"
    }
}