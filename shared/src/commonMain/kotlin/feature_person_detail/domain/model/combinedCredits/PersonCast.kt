package feature_person_detail.domain.model.combinedCredits

import core.domain.model.MediaType
import kotlinx.serialization.Serializable

@Serializable
data class PersonCast(
    val id: Int,
    val title: String,
    val popularity: Double,
    val posterPath: String?,
    val character: String,
    val mediaType: MediaType
)