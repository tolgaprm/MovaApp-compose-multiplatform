package feature_home.data.movie.remote

import core.common.dispatcher.DispatcherProvider
import core.data.dto.ApiResponse
import core.data.movie.dto.MovieDto
import core.data.util.tryResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext

class MovieRemoteDataSource(
    private val httpClient: HttpClient,
    private val dispatcherProvider: DispatcherProvider
) {
    suspend fun getNowPlayingMovies(
        language: String,
        page: Int
    ): ApiResponse<MovieDto> {
        return withContext(dispatcherProvider.IO) {
            tryResult<ApiResponse<MovieDto>> {
                httpClient.get("movie/now_playing") {
                    parameter("language", language)
                    parameter("page", page)
                }
            }
        }
    }

    suspend fun getPopularMovies(
        language: String,
        page: Int
    ): ApiResponse<MovieDto> {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get("movie/popular") {
                    parameter("language", language)
                    parameter("page", page)
                }
            }
        }
    }

    suspend fun getTopRatedMovies(
        language: String,
        page: Int
    ): ApiResponse<MovieDto> {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get("movie/top_rated") {
                    parameter("language", language)
                    parameter("page", page)
                }
            }
        }
    }
}