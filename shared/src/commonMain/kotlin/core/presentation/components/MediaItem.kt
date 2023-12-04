package core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import core.domain.movie.models.Movie

@Composable
fun MovieItem(
    modifier: Modifier = Modifier, movie: Movie
) {
    MediaItem(
        modifier = modifier,
        posterImageUrl = movie.posterPath,
        title = movie.title,
        year = movie.releaseDate,
        voteAverage = movie.voteAverage,
        formattedVoteCount = movie.formattedVoteCount,
    )
}

@Composable
private fun MediaItem(
    modifier: Modifier = Modifier,
    posterImageUrl: String?,
    title: String,
    year: String,
    voteAverage: Double,
    formattedVoteCount: String,
) {
    Column(
        modifier = modifier.clip(RoundedCornerShape(8.dp))
            .width(165.dp)
    ) {
        MovaImage(
            imageUrl = posterImageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer)
        )
        Column(
            modifier = Modifier.fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                title,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography
                    .labelLarge.copy(fontWeight = FontWeight.Bold),
                maxLines = 1
            )
            Text(
                year,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
            )
            RatingStats(
                voteAverage = voteAverage,
                formattedVoteCount = formattedVoteCount,
            )
        }
    }
}