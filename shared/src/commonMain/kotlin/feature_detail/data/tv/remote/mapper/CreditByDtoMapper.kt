package feature_detail.data.tv.remote.mapper

import core.data.orZero
import feature_detail.data.tv.remote.dto.CreatedByDto
import feature_detail.domain.model.credits.Director

fun CreatedByDto.toDirector(): Director {
    return Director(
        id = id.orZero(),
        name = name.orEmpty()
    )
}