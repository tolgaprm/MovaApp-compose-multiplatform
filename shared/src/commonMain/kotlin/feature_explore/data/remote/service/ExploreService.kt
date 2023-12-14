package feature_explore.data.remote.service

import core.data.dto.ApiResponse
import feature_explore.data.multiSearch.dto.SearchDto

interface ExploreService {

    suspend fun multiSearch(
        query: String,
        language: String,
        page: Int
    ): ApiResponse<SearchDto>
}