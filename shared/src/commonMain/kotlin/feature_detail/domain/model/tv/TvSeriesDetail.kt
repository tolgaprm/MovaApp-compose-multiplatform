package feature_detail.domain.model.tv

import feature_detail.domain.model.credits.Cast
import feature_detail.domain.model.credits.Director
import feature_detail.domain.model.watchProvider.WatchProviderItem

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
    val genresBySeparatedByComma: String? = null,
    val cast: List<Cast>,
    val directors: List<Director>,
    val watchProviderItem: WatchProviderItem?
)