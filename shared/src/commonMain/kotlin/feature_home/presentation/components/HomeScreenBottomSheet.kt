package feature_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Details
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.domain.movie.Movie
import core.domain.tvseries.TvSeries
import core.presentation.components.MovaImage
import core.presentation.theme.dimensions

@Composable
fun HomeScreenBottomSheet(
    modifier: Modifier,
    selectedMovie: Movie?,
    selectedTvSeries: TvSeries?,
    onClickClose: () -> Unit,
    onClickedDetails: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = MaterialTheme.dimensions.fourLevel)
            .padding(bottom = MaterialTheme.dimensions.fourLevel)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            MovaImage(
                imageUrl = selectedMovie?.posterPath ?: selectedTvSeries?.posterPath ?: "",
                modifier = Modifier.height(220.dp)
                    .width(120.dp)
                    .clip(MaterialTheme.shapes.small)
            )

            Column(
                modifier = Modifier
                    .padding(
                        start = MaterialTheme.dimensions.fourLevel,
                        end = MaterialTheme.dimensions.twoLevel
                    )
            ) {
                BottomSheetTop(
                    modifier = Modifier.fillMaxWidth(),
                    title = selectedMovie?.title ?: selectedTvSeries?.name ?: "",
                    onClickClose = onClickClose
                )

                Text(
                    text = selectedMovie?.releaseDate ?: selectedTvSeries?.releaseDate ?: "",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f),
                    modifier = Modifier.padding(bottom = MaterialTheme.dimensions.threeLevel)
                )

                Text(
                    text = selectedMovie?.overview ?: selectedTvSeries?.overview ?: "",
                    maxLines = 7,
                    overflow = Ellipsis
                )
            }
        }

        BottomSheetButton(
            modifier = Modifier
                .padding(top = MaterialTheme.dimensions.fourLevel)
                .fillMaxWidth(),
            buttonText = selectedMovie?.let {
                "Details and More"
            } ?: selectedTvSeries?.let {
                "Episodes and More"
            } ?: "",
            onClicked = onClickedDetails
        )
    }
}

@Composable
private fun BottomSheetTop(
    modifier: Modifier = Modifier,
    title: String,
    onClickClose: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp),
            maxLines = 1,
            overflow = Ellipsis,
            modifier = Modifier.weight(8f)
        )

        IconButton(
            onClick = onClickClose,
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .weight(2f)

        ) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = "Close",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun BottomSheetButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    onClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .clickable(onClick = onClicked)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(MaterialTheme.dimensions.fourLevel),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.twoLevel)
        ) {
            Icon(
                imageVector = Icons.Rounded.Details,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = buttonText,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = MaterialTheme.dimensions.twoLevel)
            )
        }
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Default.ArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}