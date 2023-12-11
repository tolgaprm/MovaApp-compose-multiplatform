package feature_detail.domain.tv.model

data class TvSeriesDetail(
    val id: Int,
    val posterPath: String?,
    val releaseDate: String,
    val name: String,
    val numberOfSeasons: Int,
    val inProduction: Boolean,
    val lastAirDate: String,
    val overview: String,
    val voteAverage: Double,
    val formattedVoteCount: String,
    val genresBySeparatedByComma: String? = null
)