package feature_person_detail.data.remote.mapper

import core.data.orZero
import core.data.util.DateTimeUtil
import feature_person_detail.data.remote.dto.PersonDetailDto
import feature_person_detail.domain.model.PersonDetail

fun PersonDetailDto.toPersonDetail(): PersonDetail {
    return PersonDetail(
        id = id.orZero(),
        name = name.orEmpty(),
        biography = biography.orEmpty(),
        birthday = DateTimeUtil.convertDateFormat(birthday),
        deathDay = DateTimeUtil.convertDateFormat(deathday),
        placeOfBirth = placeOfBirth.orEmpty(),
        profilePath = profilePath
    )
}