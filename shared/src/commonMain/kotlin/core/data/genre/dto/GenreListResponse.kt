package core.data.genre.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenreListResponse(
    val genres: List<GenreDto>
)