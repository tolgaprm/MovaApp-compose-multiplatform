package core.data.genre.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    val id: Int?,
    val name: String?
)