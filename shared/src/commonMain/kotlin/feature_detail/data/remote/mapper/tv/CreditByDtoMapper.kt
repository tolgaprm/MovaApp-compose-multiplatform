package feature_detail.data.remote.mapper.tv

import core.data.util.orZero
import feature_detail.data.remote.dto.tv.CreatedByDto
import feature_detail.domain.model.credits.Director

fun CreatedByDto.toDirector(): Director {
    return Director(
        id = id.orZero(),
        name = name.orEmpty()
    )
}