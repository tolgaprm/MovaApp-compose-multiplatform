package core.domain.movie.models

import core.domain.util.GenreUtil

data class Movie(
    val id: Int,
    val genreIds: List<Int>,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val genresBySeparatedByComma: String? = null
) {
    val formattedVoteCount: String
        get() = GenreUtil.formatVoteCount(voteCount)
}