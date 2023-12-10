package feature_detail.data.remote.tv.dto

import core.data.genre.dto.GenreDto
import feature_detail.data.remote.dto.ProductionCompanyDto
import feature_detail.data.remote.dto.ProductionCountry
import feature_detail.data.remote.dto.SpokenLanguageDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvSeriesDetailDto(
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("created_by") val createdByDto: List<CreatedByDto>?,
    @SerialName("episode_run_time") val episodeRunTime: List<Int>?,
    @SerialName("first_air_date") val firstAirDate: String?,
    val genres: List<GenreDto>?,
    val homepage: String?,
    val id: Int?,
    @SerialName("in_production") val inProduction: Boolean?,
    val languages: List<String>?,
    @SerialName("last_air_date") val lastAirDate: String?,
    @SerialName("last_episode_to_air") val lastEpisodeToAirDto: LastEpisodeToAirDto?,
    val name: String?,
    @SerialName("next_episode_to_air") val nextEpisodeToAir: String?,
    val networks: List<NetworkDto>?,
    @SerialName("number_of_episodes") val numberOfEpisodes: Int?,
    @SerialName("number_of_seasons") val numberOfSeasons: Int?,
    @SerialName("origin_country") val originCountry: List<String>?,
    @SerialName("original_language") val originalLanguage: String?,
    @SerialName("original_name") val originalName: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    @SerialName("production_companies") val productionCompanies: List<ProductionCompanyDto>?,
    @SerialName("production_countries") val productionCountries: List<ProductionCountry>?,
    val seasons: List<SeasonDto>?,
    @SerialName("spoken_languages") val spokenLanguages: List<SpokenLanguageDto>?,
    val status: String?,
    val tagline: String?,
    val type: String?,
    @SerialName("vote_average") val voteAverage: Double?,
    @SerialName("vote_count") val voteCount: Int?
)