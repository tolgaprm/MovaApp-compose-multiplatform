package feature_detail.data.remote.dto.watchProvider

import kotlinx.serialization.Serializable

@Serializable
data class WatchProvidersDto(
    val results: WatchProviderRegionDto?
)