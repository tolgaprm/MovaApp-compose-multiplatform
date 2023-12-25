package feature_explore.data.remote.service

import core.data.remote.dto.ApiResponse
import core.data.remote.dto.movie.MovieDto
import core.data.remote.dto.tv.TvSeriesDto
import feature_explore.data.remote.dto.multiSearch.SearchDto

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