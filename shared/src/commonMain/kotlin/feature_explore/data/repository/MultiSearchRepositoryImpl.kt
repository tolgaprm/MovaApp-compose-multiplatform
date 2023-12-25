package feature_explore.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import core.common.Constants
import feature_explore.data.remote.dataSource.MultiSearchRemoteDataSource
import feature_explore.data.remote.pagingSource.MultiSearchPagingSource
import feature_explore.domain.model.MultiSearch
import feature_explore.domain.repository.MultiSearchRepository
import kotlinx.coroutines.flow.Flow

class MultiSearchRepositoryImpl(
    private val multiSearchRemoteDataSource: MultiSearchRemoteDataSource
) : MultiSearchRepository {
    override fun multiSearch(
        query: String,
        language: String
    ): Flow<PagingData<MultiSearch>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.ITEMS_PER_PAGE),
            pagingSourceFactory = {
                MultiSearchPagingSource(
                    multiSearchRemoteDataSource,
                    query,
                    language
                )
            }
        ).flow
    }
}