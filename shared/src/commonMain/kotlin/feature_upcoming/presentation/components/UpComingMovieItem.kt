package feature_upcoming.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import core.common.ImageSize
import core.domain.movie.Movie
import core.presentation.components.GenresView
import core.presentation.components.MovaImage
import core.presentation.theme.dimensions

@Composable
fun UpComingMovieItem(
    modifier: Modifier = Modifier,
    movie: Movie
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            UpComingPoster(
                modifier = modifier,
                posterPath = movie.posterPath
            )

            UpComingMovieInfo(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(MaterialTheme.dimensions.fourLevel),
                movie = movie
            )
        }
    }
}

@Composable
private fun UpComingPoster(
    modifier: Modifier = Modifier,
    posterPath: String?
) {
    Box(
        modifier = modifier
            .height(200.dp)
    ) {
        MovaImage(
            imageUrl = posterPath,
            modifier = Modifier.fillMaxWidth()
                .alpha(0.4f),
            imageSize = ImageSize.W300
        )

        MovaImage(
            modifier = Modifier
                .width(200.dp)
                .align(Alignment.Center),
            imageUrl = posterPath,
            imageSize = ImageSize.W300
        )
    }
}


@Composable
private fun UpComingMovieInfo(modifier: Modifier, movie: Movie) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = movie.title,
                modifier = Modifier.weight(8f),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.width(MaterialTheme.dimensions.twoLevel))

            InfoButton(
                modifier = Modifier.weight(2f),
                onClick = { }
            )
        }

        Text(
            text = movie.releaseDate,
            modifier = Modifier.padding(top = MaterialTheme.dimensions.twoLevel),
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f)
        )

        Text(
            text = movie.overview,
            modifier = Modifier.padding(top = MaterialTheme.dimensions.twoLevel),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.twoLevel))

        GenresView(
            genresSeparatedByComma = movie.genresBySeparatedByComma,
        )
    }
}

@Composable
private fun InfoButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.clickable(onClick = onClick),
            imageVector = Icons.Outlined.Info,
            contentDescription = "Show more info"
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.twoLevel))
        Text(
            text = "Info",
            modifier = Modifier,
            style = MaterialTheme.typography.labelSmall
        )
    }
}