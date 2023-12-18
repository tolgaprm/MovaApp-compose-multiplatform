package feature_explore.data.remote.service

import core.common.dispatcher.DispatcherProvider
import core.data.addCommonParameters
import core.data.dto.ApiResponse
import core.data.movie.MovieDto
import core.data.tvseries.TvSeriesDto
import core.data.util.tryResult
import feature_explore.data.multiSearch.dto.SearchDto
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext

class ExploreServiceImpl(
    private val httpClient: HttpClient,
    private val dispatcherProvider: DispatcherProvider
) : ExploreService {
    override suspend fun multiSearch(
        query: String,
        language: String,
        page: Int
    ): ApiResponse<SearchDto> {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get(MULTI_SEARCH) {
                    addCommonParameters(language = language, page = page)
                    parameter(QUERY_PARAM, query)
                }
            }
        }
    }

    override suspend fun discoverMovie(
        language: String,
        page: Int,
        withGenres: String,
        sortBy: String
    ): ApiResponse<MovieDto> {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get(DISCOVER_MOVIE) {
                    addCommonParameters(language = language, page = page)
                    addWithGenreParam(withGenres = withGenres)
                    addSortByParam(sortBy = sortBy)
                }
            }
        }
    }

    override suspend fun discoverTvSeries(
        language: String,
        page: Int,
        withGenres: String,
        sortBy: String
    ): ApiResponse<TvSeriesDto> {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get(DISCOVER_TV_SERIES) {
                    addCommonParameters(language = language, page = page)
                    addWithGenreParam(withGenres = withGenres)
                    addSortByParam(sortBy = sortBy)
                }
            }
        }
    }

    private fun HttpRequestBuilder.addWithGenreParam(withGenres: String) {
        parameter(WITH_GENRES_PARAM, withGenres)
    }

    private fun HttpRequestBuilder.addSortByParam(sortBy: String) {
        parameter(SORT_BY_PARAM, sortBy)
    }

    companion object {
        const val MULTI_SEARCH = "search/multi"
        const val QUERY_PARAM = "query"
        const val DISCOVER_MOVIE = "discover/movie"
        const val DISCOVER_TV_SERIES = "discover/tv"
        const val WITH_GENRES_PARAM = "with_genres"
        const val SORT_BY_PARAM = "sort_by"
    }
}