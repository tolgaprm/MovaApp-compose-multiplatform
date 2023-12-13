package core.data.genre.service

import core.common.dispatcher.DispatcherProvider
import core.data.addLanguageParameter
import core.data.genre.dto.GenreListResponse
import core.data.util.tryResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.withContext

class GenreServiceImpl(
    private val httpClient: HttpClient,
    private val dispatcherProvider: DispatcherProvider
) : GenreService {
    override suspend fun getMovieGenreList(language: String): GenreListResponse {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get(MOVIE_GENRE_LIST) {
                    addLanguageParameter(language)
                }.body()
            }
        }
    }

    override suspend fun getTvSeriesGenreList(language: String): GenreListResponse {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get(TV_SERIES_GENRE_LIST) {
                    addLanguageParameter(language)
                }
            }
        }
    }

    companion object {
        private const val MOVIE_GENRE_LIST = "genre/movie/list"
        private const val TV_SERIES_GENRE_LIST = "genre/tv/list"
    }
}