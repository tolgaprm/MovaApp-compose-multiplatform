package feature_home.data.remote.service

import core.data.remote.dto.ApiResponse
import core.data.remote.dto.movie.MovieDto
import core.data.remote.dto.tv.TvSeriesDto

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