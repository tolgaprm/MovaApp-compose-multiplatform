package feature_home.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.common.ImageSize
import core.domain.movie.Movie
import core.presentation.components.MovaImage
import core.presentation.components.RatingStats
import core.presentation.components.verticalGradientRect
import core.presentation.theme.dimensions

@Composable
fun NowPlayingItem(
    movie: Movie,
    onClickMovie: (Movie) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .width(400.dp)
            .fillMaxHeight()
            .clickable(onClick = { onClickMovie(movie) })
    ) {
        MovaImage(
            imageUrl = movie.posterPath,
            imageSize = ImageSize.ORIGINAL,
            modifier = Modifier.fillMaxSize()
                .clip(MaterialTheme.shapes.small)
        )

        NowPlayingItemContent(
            movie = movie,
            modifier = Modifier.verticalGradientRect()
        )
    }
}

@Composable
private fun BoxScope.NowPlayingItemContent(
    modifier: Modifier = Modifier,
    movie: Movie
) {
    Column(
        modifier = modifier.fillMaxWidth()
            .align(alignment = Alignment.BottomStart)
            .padding(MaterialTheme.dimensions.fourLevel)
    ) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.headlineSmall,
            maxLines = 1,
            color = Color.White
        )

        movie.genresBySeparatedByComma?.let {
            Text(
                text = movie.genresBySeparatedByComma,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                color = Color.White,
                modifier = Modifier.padding(vertical = MaterialTheme.dimensions.oneLevel)
            )
        }

        RatingStats(
            formattedVoteCount = movie.formattedVoteCount,
            voteAverage = movie.voteAverage,
            textColor = Color.White
        )
    }
}