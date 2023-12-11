package feature_detail.domain.tv

import core.common.Resource
import feature_detail.domain.tv.model.TvSeriesDetail

interface TvSeriesDetailRepository {
    suspend fun getTvSeriesDetail(
        id: Int,
        language: String = "en"
    ): Resource<TvSeriesDetail>
}