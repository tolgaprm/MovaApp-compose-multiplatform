package feature_home.data.remote.dataSource

import core.data.remote.dto.ApiResponse
import core.data.remote.dto.tv.TvSeriesDto
import feature_home.data.remote.service.HomeService

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