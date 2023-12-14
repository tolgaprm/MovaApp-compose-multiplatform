package feature_explore.data.multiSearch

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import core.common.Constants
import feature_explore.data.multiSearch.remote.MultiSearchRemoteDataSource
import feature_explore.domain.multiSearch.MultiSearch
import feature_explore.domain.multiSearch.MultiSearchRepository
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