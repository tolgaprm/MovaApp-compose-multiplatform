package feature_detail.data.remote.dto.watchProvider

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WatchProviderRegionDto(
    @SerialName("US") val us: WatchProviderItemDto?,
)