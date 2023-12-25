package feature_detail.data.remote.dataSource

import androidx.paging.PagingData
import core.data.util.paging.getPagingTvSeries
import core.domain.model.tv.TvSeries
import feature_detail.data.remote.dto.tv.TvSeriesDetailDto
import feature_detail.data.remote.service.DetailService
import kotlinx.coroutines.flow.Flow

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

    fun getTvSeriesRecommendations(
        id: Int,
        language: String
    ): Flow<PagingData<TvSeries>> {
        return getPagingTvSeries { page ->
            detailService.getTvSeriesRecommendations(
                tvSeriesId = id,
                language = language,
                page = page
            )
        }
    }
}