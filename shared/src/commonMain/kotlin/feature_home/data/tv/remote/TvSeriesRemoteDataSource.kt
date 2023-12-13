package feature_home.data.tv.remote

import core.data.dto.ApiResponse
import core.data.tvseries.TvSeriesDto
import feature_home.data.service.HomeService

class TvSeriesRemoteDataSource(
    private val homeService: HomeService
) {
    suspend fun getPopularTvSeries(
        language: String,
        page: Int
    ): ApiResponse<TvSeriesDto> {
        return homeService.getPopularTvSeries(language = language, page = page)
    }

    suspend fun getTopRatedTvSeries(
        language: String,
        page: Int
    ): ApiResponse<TvSeriesDto> {
        return homeService.getTopRatedTvSeries(language = language, page = page)
    }
}