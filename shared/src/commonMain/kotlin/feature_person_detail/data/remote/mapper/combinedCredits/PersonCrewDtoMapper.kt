package feature_person_detail.data.remote.mapper.combinedCredits

import core.data.util.orZero
import core.domain.model.MediaType
import feature_person_detail.data.remote.dto.combinedCredits.PersonCrewDto
import feature_person_detail.domain.model.combinedCredits.PersonCrew

fun PersonCrewDto.toPersonCrew(): PersonCrew {
    return PersonCrew(
        id = id.orZero(),
        title = title.orEmpty(),
        popularity = popularity.orZero(),
        posterPath = posterPath,
        mediaType = MediaType.fromValue(mediaType?.lowercase().orEmpty()),
        department = department.orEmpty(),
        job = job.orEmpty()
    )
}