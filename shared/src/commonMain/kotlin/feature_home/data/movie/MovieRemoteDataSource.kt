package feature_home.data.movie

import core.common.dispatcher.DispatcherProvider
import core.data.addCommonParameters
import core.data.dto.ApiResponse
import core.data.movie.MovieDto
import core.data.util.tryResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
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
                httpClient.get(MOVIE_NOW_PLAYING) {
                    addCommonParameters(language = language, page = page)
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
                httpClient.get(MOVIE_POPULAR) {
                    addCommonParameters(language = language, page = page)
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
                httpClient.get(MOVIE_TOP_RATED) {
                    addCommonParameters(language = language, page = page)
                }
            }
        }
    }

    companion object {
        const val MOVIE_NOW_PLAYING = "movie/now_playing"
        const val MOVIE_POPULAR = "movie/popular"
        const val MOVIE_TOP_RATED = "movie/top_rated"
    }
}