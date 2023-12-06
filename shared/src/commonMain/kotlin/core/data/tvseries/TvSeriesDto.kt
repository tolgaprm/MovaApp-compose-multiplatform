package core.data.tvseries

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvSeriesDto(
    val id: Int?,
    val popularity: Double?,
    val overview: String?,
    val name: String?,
    @SerialName("original_name") val originalName: String?,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("first_air_date") val firstAirDate: String?,
    @SerialName("origin_country") val originCountry: List<String>?,
    @SerialName("genre_ids") val genreIds: List<Int>?,
    @SerialName("original_language") val originalLanguage: String?,
    @SerialName("vote_average") val voteAverage: Double?,
    @SerialName("vote_count") val voteCount: Int?,
)