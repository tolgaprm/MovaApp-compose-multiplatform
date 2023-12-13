package feature_home.data.service

import core.data.dto.ApiResponse
import core.data.movie.MovieDto
import core.data.tvseries.TvSeriesDto

interface HomeService {

    suspend fun getNowPlayingMovies(
        language: String,
        page: Int
    ): ApiResponse<MovieDto>

    suspend fun getPopularMovies(
        language: String,
        page: Int
    ): ApiResponse<MovieDto>

    suspend fun getTopRatedMovies(
        language: String,
        page: Int
    ): ApiResponse<MovieDto>

    suspend fun getPopularTvSeries(
        language: String,
        page: Int
    ): ApiResponse<TvSeriesDto>

    suspend fun getTopRatedTvSeries(
        language: String,
        page: Int
    ): ApiResponse<TvSeriesDto>
}