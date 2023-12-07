package core.domain.movie

data class Movie(
    val id: Int,
    val genreIds: List<Int>,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String,
    val voteAverage: Double,
    val formattedVoteCount: String,
    val genresBySeparatedByComma: String? = null
)