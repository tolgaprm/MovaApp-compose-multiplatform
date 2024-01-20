package feature_person_detail.domain.model.combinedCredits

import core.domain.model.MediaType

open class PersonWorks(
    open val id: Int,
    open val title: String,
    open val popularity: Double,
    open val posterPath: String?,
    open val mediaType: MediaType,
    val personWorksType: PersonWorksType,
)

enum class PersonWorksType {
    CAST,
    CREW
}