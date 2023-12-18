package feature_explore.data.remote.service

import core.data.dto.ApiResponse
import core.data.movie.MovieDto
import core.data.tvseries.TvSeriesDto
import feature_explore.data.multiSearch.dto.SearchDto

interface ExploreService {

    suspend fun multiSearch(
        query: String,
        language: String,
        page: Int
    ): ApiResponse<SearchDto>

    suspend fun discoverMovie(
        language: String,
        page: Int,
        withGenres: String = "",
        sortBy: String
    ): ApiResponse<MovieDto>


    suspend fun discoverTvSeries(
        language: String,
        page: Int,
        withGenres: String = "",
        sortBy: String
    ): ApiResponse<TvSeriesDto>
}