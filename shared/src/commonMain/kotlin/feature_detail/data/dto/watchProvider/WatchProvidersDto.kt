package feature_detail.data.dto.watchProvider

import kotlinx.serialization.Serializable

@Serializable
data class WatchProvidersDto(
    val results: WatchProviderRegionDto?
)