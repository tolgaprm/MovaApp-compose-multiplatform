package feature_home.data.remote.service

import core.common.dispatcher.DispatcherProvider
import core.data.remote.dto.ApiResponse
import core.data.remote.dto.movie.MovieDto
import core.data.remote.dto.tv.TvSeriesDto
import core.data.util.addCommonParameters
import core.data.util.tryResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.withContext

class HomeServiceImpl(
    private val httpClient: HttpClient,
    private val dispatcherProvider: DispatcherProvider
) : HomeService {
    override suspend fun getNowPlayingMovies(language: String, page: Int): ApiResponse<MovieDto> {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get(MOVIE_NOW_PLAYING) {
                    addCommonParameters(language = language, page = page)
                }
            }
        }
    }

    override suspend fun getPopularMovies(language: String, page: Int): ApiResponse<MovieDto> {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get(MOVIE_POPULAR) {
                    addCommonParameters(language = language, page = page)
                }
            }
        }
    }

    override suspend fun getTopRatedMovies(language: String, page: Int): ApiResponse<MovieDto> {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get(MOVIE_TOP_RATED) {
                    addCommonParameters(language = language, page = page)
                }
            }
        }
    }

    override suspend fun getPopularTvSeries(language: String, page: Int): ApiResponse<TvSeriesDto> {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get(TV_POPULAR) {
                    addCommonParameters(language = language, page = page)
                }
            }
        }
    }

    override suspend fun getTopRatedTvSeries(
        language: String,
        page: Int
    ): ApiResponse<TvSeriesDto> {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get(TV_TOP_RATED) {
                    addCommonParameters(language = language, page = page)
                }
            }
        }
    }

    companion object {
        const val MOVIE_NOW_PLAYING = "movie/now_playing"
        const val MOVIE_POPULAR = "movie/popular"
        const val MOVIE_TOP_RATED = "movie/top_rated"
        const val TV_POPULAR = "tv/popular"
        const val TV_TOP_RATED = "tv/top_rated"
    }
}