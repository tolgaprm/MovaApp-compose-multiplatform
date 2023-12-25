package feature_home.data.repository

import app.cash.paging.PagingData
import core.data.util.paging.getPagingTvSeries
import core.domain.model.tv.TvSeries
import feature_home.data.remote.dataSource.TvSeriesRemoteDataSource
import feature_home.domain.repository.HomeTvSeriesRepository
import kotlinx.coroutines.flow.Flow

class HomeTvSeriesRepositoryImpl(
    private val tvSeriesRemoteDataSource: TvSeriesRemoteDataSource
) : HomeTvSeriesRepository {
    override fun getPopularTvSeries(language: String): Flow<PagingData<TvSeries>> {
        return getPagingTvSeries { page ->
            tvSeriesRemoteDataSource.getPopularTvSeries(
                language = language,
                page = page
            )
        }
    }

    override fun getTopRatedTvSeries(language: String): Flow<PagingData<TvSeries>> {
        return getPagingTvSeries { page ->
            tvSeriesRemoteDataSource.getTopRatedTvSeries(
                language = language,
                page = page
            )
        }
    }
}