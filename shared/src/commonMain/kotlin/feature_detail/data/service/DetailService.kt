package feature_detail.data.service

import feature_detail.data.movie.remote.dto.MovieDetailDto
import feature_detail.data.tv.remote.dto.TvSeriesDetailDto

interface DetailService {

    suspend fun getMovieDetail(
        id: Int,
        language: String
    ): MovieDetailDto

    suspend fun getTvSeriesDetail(
        tvSeriesId: Int,
        language: String
    ): TvSeriesDetailDto
}