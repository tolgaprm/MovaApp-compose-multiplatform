package feature_detail.data.tv.remote

import androidx.paging.PagingData
import core.data.util.getPagingTvSeries
import core.domain.tvseries.TvSeries
import feature_detail.data.service.DetailService
import feature_detail.data.tv.remote.dto.TvSeriesDetailDto
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