package feature_person_detail.data.remote.mapper.combinedCredits

import core.data.util.orZero
import core.domain.model.MediaType
import feature_person_detail.data.remote.dto.combinedCredits.PersonCastDto
import feature_person_detail.domain.model.combinedCredits.PersonCast

fun PersonCastDto.toPersonCast(): PersonCast {
    return PersonCast(
        id = id.orZero(),
        title = title.orEmpty(),
        popularity = popularity.orZero(),
        posterPath = posterPath,
        character = character.orEmpty(),
        mediaType = MediaType.fromValue(mediaType?.lowercase().orEmpty())
    )
}