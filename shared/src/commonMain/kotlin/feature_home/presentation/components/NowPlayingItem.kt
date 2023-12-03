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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.common.ImageSize
import core.domain.movie.models.Movie
import core.presentation.components.MovaImage
import core.presentation.components.RatingStats

@Composable
fun NowPlayingItem(
    movie: Movie,
    onClickMovie: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .width(400.dp)
            .fillMaxHeight()
            .clickable(onClick = { onClickMovie(movie.id) })
    ) {
        MovaImage(
            imageUrl = movie.posterPath,
            imageSize = ImageSize.ORIGINAL,
            modifier = Modifier.fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
        )

        NowPlayingItemContent(
            movie = movie,
            modifier = Modifier.drawBehind {
                drawRoundRect(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Black.copy(alpha = 0.4f),
                            Color.Black.copy(alpha = 0.6f),
                            Color.Black.copy(alpha = 0.8f)
                        )
                    )
                )
            }
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
            .padding(16.dp)
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
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        RatingStats(
            formattedVoteCount = movie.formattedVoteCount,
            voteAverage = movie.voteAverage
        )
    }
}