package core.domain.tvseries

data class TvSeries(
    val id: Int,
    val name: String,
    val posterPath: String?,
    val overview: String,
    val releaseDate: String,
    val genreIds: List<Int>,
    val voteAverage: Double,
    val formattedVoteCount: String,
    val genresBySeparatedByComma: String? = null
)