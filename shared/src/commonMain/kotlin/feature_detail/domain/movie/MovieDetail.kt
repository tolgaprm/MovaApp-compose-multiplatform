package feature_detail.domain.movie

import feature_detail.domain.model.credits.Cast
import feature_detail.domain.model.credits.Director

data class MovieDetail(
    val id: Int,
    val posterPath: String?,
    val originalTitle: String,
    val overview: String,
    val releaseDate: String,
    val runtime: Map<String, String>,
    val voteAverage: Double,
    val formattedVoteCount: String,
    val genresBySeparatedByComma: String? = null,
    val casts: List<Cast>,
    val directors: List<Director>
)