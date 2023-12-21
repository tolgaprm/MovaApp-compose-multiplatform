package feature_detail.data.dto.watchProvider

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WatchProviderRegionDto(
    @SerialName("TR") val us: WatchProviderItemDto?,
)