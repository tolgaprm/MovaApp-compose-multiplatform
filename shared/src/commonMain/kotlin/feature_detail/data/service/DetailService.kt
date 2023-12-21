package feature_detail.data.service

import core.data.dto.ApiResponse
import core.data.movie.MovieDto
import core.data.tvseries.TvSeriesDto
import feature_detail.data.movie.remote.dto.MovieDetailDto
import feature_detail.data.tv.remote.dto.TvSeriesDetailDto

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