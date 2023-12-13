package feature_detail.data.tv

import core.common.Resource
import core.data.util.tryResultReturnResource
import feature_detail.data.tv.remote.TvSeriesDetailRemoteDataSource
import feature_detail.data.tv.remote.mapper.toTvSeriesDetail
import feature_detail.domain.tv.TvSeriesDetailRepository
import feature_detail.domain.tv.model.TvSeriesDetail

class TvSeriesDetailRepositoryImpl(
    private val tvSeriesDetailRemoteDataSource: TvSeriesDetailRemoteDataSource
) : TvSeriesDetailRepository {
    override suspend fun getTvSeriesDetail(
        id: Int,
        language: String
    ): Resource<TvSeriesDetail> {
        return tryResultReturnResource {
            tvSeriesDetailRemoteDataSource.getTvSeriesDetail(
                id = id,
                language = language
            ).toTvSeriesDetail()
        }
    }
}