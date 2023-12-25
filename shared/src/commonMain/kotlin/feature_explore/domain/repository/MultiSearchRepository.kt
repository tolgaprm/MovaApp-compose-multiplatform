package feature_explore.domain.repository

import androidx.paging.PagingData
import feature_explore.domain.model.MultiSearch
import kotlinx.coroutines.flow.Flow

interface MultiSearchRepository {

    fun multiSearch(
        query: String,
        language: String = "en"
    ): Flow<PagingData<MultiSearch>>
}