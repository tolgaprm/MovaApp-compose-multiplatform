package feature_person_detail.domain.model.combinedCredits

import core.domain.model.MediaType

data class PersonCrew(
    override val id: Int,
    override val title: String,
    override val popularity: Double,
    override val posterPath: String?,
    override val mediaType: MediaType,
    val department: String,
    val job: String
) : PersonWorks(
    id = id,
    title = title,
    popularity = popularity,
    posterPath = posterPath,
    mediaType = mediaType,
    personWorksType = PersonWorksType.CREW
)