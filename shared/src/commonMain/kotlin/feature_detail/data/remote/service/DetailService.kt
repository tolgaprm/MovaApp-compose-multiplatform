package feature_detail.data.remote.service

import core.data.remote.dto.ApiResponse
import core.data.remote.dto.movie.MovieDto
import core.data.remote.dto.tv.TvSeriesDto
import feature_detail.data.remote.dto.movie.MovieDetailDto
import feature_detail.data.remote.dto.tv.TvSeriesDetailDto

interface DetailService {

    suspend fun getMovieDetail(
        movieId: Int,
        language: String
    ): MovieDetailDto

    suspend fun getTvSeriesDetail(
        tvSeriesId: Int,
        language: String
    ): TvSeriesDetailDto

    suspend fun getMovieRecommendations(
        movieId: Int,
        language: String,
        page: Int
    ): ApiResponse<MovieDto>

    suspend fun getTvSeriesRecommendations(
        tvSeriesId: Int,
        language: String,
        page: Int
    ): ApiResponse<TvSeriesDto>
}