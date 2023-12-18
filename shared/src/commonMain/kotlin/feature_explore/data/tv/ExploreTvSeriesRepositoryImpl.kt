package feature_explore.data.tv

import app.cash.paging.PagingData
import core.data.util.getPagingTvSeries
import core.domain.tvseries.TvSeries
import feature_explore.data.tv.remote.ExploreTvSeriesRemoteDataSource
import feature_explore.domain.tv.ExploreTvSeriesRepository
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