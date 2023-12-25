package feature_detail.data.remote.dto.watchProvider

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WatchProviderItemDto(
    val link: String,
    @SerialName("flatrate") val flatRate: List<WatchProviderItemDetailDto>?,
    val rent: List<WatchProviderItemDetailDto>?,
    val buy: List<WatchProviderItemDetailDto>?,
    val free: List<WatchProviderItemDetailDto>?,
)

@Serializable
data class WatchProviderItemDetailDto(
    @SerialName("display_priority") val displayPriority: Int,
    @SerialName("logo_path") val logoPath: String,
    @SerialName("provider_id") val providerId: Int,
    @SerialName("provider_name") val providerName: String,
)