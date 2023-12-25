package feature_explore.data.repository

import app.cash.paging.PagingData
import core.data.util.paging.getPagingTvSeries
import core.domain.model.tv.TvSeries
import feature_explore.data.remote.dataSource.ExploreTvSeriesRemoteDataSource
import feature_explore.domain.repository.ExploreTvSeriesRepository
import kotlinx.coroutines.flow.Flow

class ExploreTvSeriesRepositoryImpl(
    private val exploreTvSeriesRemoteDataSource: ExploreTvSeriesRemoteDataSource
) : ExploreTvSeriesRepository {
    override fun discoverTvSeries(
        language: String,
        withGenres: String,
        sortBy: String
    ): Flow<PagingData<TvSeries>> {
        return getPagingTvSeries { page ->
            exploreTvSeriesRemoteDataSource.discoverTvSeries(
                language = language,
                page = page,
                withGenres = withGenres,
                sortBy = sortBy
            )
        }
    }
}