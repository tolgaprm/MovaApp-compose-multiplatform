package feature_detail.data.repository

import androidx.paging.PagingData
import core.common.Resource
import core.data.util.tryResultReturnResource
import core.domain.model.tv.TvSeries
import feature_detail.data.remote.dataSource.TvSeriesDetailRemoteDataSource
import feature_detail.data.remote.mapper.tv.toTvSeriesDetail
import feature_detail.domain.model.tv.TvSeriesDetail
import feature_detail.domain.repository.TvSeriesDetailRepository
import kotlinx.coroutines.flow.Flow

class TvSeriesDetailRepositoryImpl(
    private val tvSeriesDetailRemoteDataSource: TvSeriesDetailRemoteDataSource
) : TvSeriesDetailRepository {
    override suspend fun getTvSeriesDetail(
        id: Int,
        language: String,
        countryIsoCode: String
    ): Resource<TvSeriesDetail> {
        return tryResultReturnResource {
            tvSeriesDetailRemoteDataSource.getTvSeriesDetail(
                id = id,
                language = language
            ).toTvSeriesDetail(countryIsoCode)
        }
    }

    override fun getTvSeriesRecommendations(id: Int, language: String): Flow<PagingData<TvSeries>> {
        return tvSeriesDetailRemoteDataSource.getTvSeriesRecommendations(
            id = id,
            language = language
        )
    }
}