package feature_detail.data.remote.dto.tv

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkDto(
    val id: Int?,
    val name: String?,
    @SerialName("logo_path") val logoPath: String?,
    @SerialName("origin_country") val originCountry: String?
)
