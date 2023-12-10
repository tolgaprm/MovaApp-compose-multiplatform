package feature_detail.data.remote.tv.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatedByDto(
    val id: Int?,
    @SerialName("credit_id") val creditId: String?,
    val name: String?,
    val gender: Int?,
    @SerialName("profile_path") val profilePath: String?
)