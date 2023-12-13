package feature_home.data.tv

import app.cash.paging.PagingData
import core.data.util.getPagingTvSeries
import core.domain.tvseries.TvSeries
import feature_home.data.tv.remote.TvSeriesRemoteDataSource
import feature_home.domain.tv.HomeTvSeriesRepository
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