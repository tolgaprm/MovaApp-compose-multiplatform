package feature_detail.presentation.components.recommendations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.domain.model.movie.Movie
import core.presentation.components.search.SearchItem
import core.presentation.components.search.SearchItemType

@Composable
fun MovieRecommendationItem(
    modifier: Modifier = Modifier,
    movie: Movie
) {
    SearchItem(
        modifier = modifier,
        posterImageUrl = movie.posterPath,
        title = movie.title,
        formattedVoteCount = movie.formattedVoteCount,
        voteAverage = movie.voteAverage,
        searchItemType = SearchItemType.MOVIE
    )
}