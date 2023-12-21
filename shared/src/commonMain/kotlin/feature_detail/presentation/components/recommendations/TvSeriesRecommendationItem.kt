package feature_detail.presentation.components.recommendations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.domain.tvseries.TvSeries
import core.presentation.components.search.SearchItem
import core.presentation.components.search.SearchItemType

@Composable
fun TvSeriesRecommendationItem(
    modifier: Modifier = Modifier,
    tvSeries: TvSeries
) {
    SearchItem(
        modifier = modifier,
        posterImageUrl = tvSeries.posterPath,
        title = tvSeries.name,
        formattedVoteCount = tvSeries.formattedVoteCount,
        voteAverage = tvSeries.voteAverage,
        searchItemType = SearchItemType.TV_SERIES
    )
}