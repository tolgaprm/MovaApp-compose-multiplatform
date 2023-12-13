package feature_detail.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpokenLanguageDto(
    @SerialName("english_name") val englishName: String?,
    @SerialName("iso_639_1") val iso6391: String?,
    val name: String?
)