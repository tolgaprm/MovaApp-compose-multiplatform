package feature_detail.data.tv.remote

import feature_detail.data.service.DetailService
import feature_detail.data.tv.remote.dto.TvSeriesDetailDto

class TvSeriesDetailRemoteDataSource(
    private val detailService: DetailService
) {
    suspend fun getTvSeriesDetail(
        id: Int,
        language: String
    ): TvSeriesDetailDto {
        return detailService.getTvSeriesDetail(
            tvSeriesId = id,
            language = language
        )
    }
}