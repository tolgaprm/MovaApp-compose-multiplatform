package feature_detail.domain.movie.model

import feature_detail.domain.model.credits.Cast
import feature_detail.domain.model.credits.Director
import feature_detail.domain.model.watchProvider.WatchProviderItem

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
    val directors: List<Director>,
    val watchProviderItem: WatchProviderItem?
)