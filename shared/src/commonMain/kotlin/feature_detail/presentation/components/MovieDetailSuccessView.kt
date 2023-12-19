package feature_detail.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature_detail.domain.movie.MovieDetail

@Composable
fun MovieDetailSuccessView(
    modifier: Modifier = Modifier,
    movieDetail: MovieDetail,
    onClickedCastItem: (Int) -> Unit
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
        castOfList = movieDetail.credit.cast,
        onClickedCastItem = onClickedCastItem
    )
}