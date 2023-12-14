package feature_explore.domain.multiSearch

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface MultiSearchRepository {

    fun multiSearch(
        query: String,
        language: String = "en"
    ): Flow<PagingData<MultiSearch>>
}