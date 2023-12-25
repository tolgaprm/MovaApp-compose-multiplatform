package core.data.remote.dto.genre

import kotlinx.serialization.Serializable

@Serializable
data class GenreListResponse(
    val genres: List<GenreDto>
)