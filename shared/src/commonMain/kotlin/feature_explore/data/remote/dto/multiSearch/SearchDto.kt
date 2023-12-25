package feature_explore.data.remote.dto.multiSearch

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchDto(
    val adult: Boolean?,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("first_air_date") val firstAirDate: String?,
    val gender: Int?,
    @SerialName("genre_ids") val genreIds: List<Int>?,
    val id: Int?,
    @SerialName("known_for") val knownForDto: List<KnownForDto?>?,
    @SerialName("known_for_department") val knownForDepartment: String?,
    @SerialName("media_type") val mediaType: String?,
    val name: String?,
    @SerialName("origin_country") val originCountry: List<String>?,
    @SerialName("original_language") val originalLanguage: String?,
    @SerialName("original_name") val originalName: String?,
    @SerialName("original_title") val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("profile_path") val profilePath: String?,
    @SerialName("release_date") val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    @SerialName("vote_average") val voteAverage: Double?,
    @SerialName("vote_count") val voteCount: Int?
)