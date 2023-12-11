package feature_detail.domain.movie

data class MovieDetail(
    val id: Int,
    val posterPath: String?,
    val originalTitle: String,
    val overview: String,
    val releaseDate: String,
    val runtime: Map<String, String>,
    val voteAverage: Double,
    val formattedVoteCount: String,
    val genresBySeparatedByComma: String? = null
)