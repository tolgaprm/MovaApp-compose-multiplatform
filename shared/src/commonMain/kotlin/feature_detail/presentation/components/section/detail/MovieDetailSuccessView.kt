package feature_detail.presentation.components.section.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.cash.paging.compose.LazyPagingItems
import core.domain.model.movie.Movie
import core.presentation.theme.dimensions
import feature_detail.domain.model.movie.MovieDetail
import feature_detail.presentation.components.recommendations.MovieRecommendationItem

@Composable
fun MovieDetailSuccessView(
    modifier: Modifier = Modifier,
    movieDetail: MovieDetail,
    movieRecommendations: LazyPagingItems<Movie>,
    onClickedCastItem: (Int) -> Unit,
    onClickedDirector: (Int) -> Unit,
    onClickedRecommendationItem: (Movie) -> Unit
) {
    DetailSuccessSection(
        modifier = modifier,
        posterPath = movieDetail.posterPath,
        originalTitle = movieDetail.originalTitle,
        formattedVoteCount = movieDetail.formattedVoteCount,
        voteAverage = movieDetail.voteAverage,
        genresBySeparatedByComma = movieDetail.genresBySeparatedByComma,
        runtime = movieDetail.runtime,
        overview = movieDetail.overview,
        releaseDateSection = {
            Text(
                text = movieDetail.releaseDate,
                style = MaterialTheme.typography.labelMedium
            )
        },
        castOfList = movieDetail.casts,
        onClickedCastItem = onClickedCastItem,
        directors = movieDetail.directors,
        onClickedDirector = onClickedDirector,
        watchProviderItem = movieDetail.watchProviderItem,
        pagingItems = movieRecommendations,
        pagingItemComponent = { movie ->
            MovieRecommendationItem(
                modifier = Modifier.padding(horizontal = MaterialTheme.dimensions.fourLevel)
                    .clickable {
                        onClickedRecommendationItem(movie)
                    },
                movie = movie
            )
        }
    )
}