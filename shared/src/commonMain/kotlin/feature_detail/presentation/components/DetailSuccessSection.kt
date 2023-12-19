package feature_detail.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import core.data.util.hourKey
import core.data.util.minutesKey
import core.presentation.components.GenresView
import core.presentation.components.MovaImage
import core.presentation.components.RatingStats
import core.presentation.theme.dimensions
import feature_detail.domain.model.credits.Cast
import feature_detail.domain.model.credits.Director

@Composable
fun DetailSuccessSection(
    modifier: Modifier = Modifier,
    posterPath: String?,
    originalTitle: String,
    formattedVoteCount: String,
    voteAverage: Double,
    genresBySeparatedByComma: String?,
    overview: String,
    runtime: Map<String, String>? = null,
    castOfList: List<Cast>,
    directors: List<Director>,
    onClickedCastItem: (Int) -> Unit,
    onClickedDirector: (Int) -> Unit,
    releaseDateSection: @Composable () -> Unit = {},
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        MovaImage(
            imageUrl = posterPath, modifier = Modifier.fillMaxWidth().height(500.dp)
        )
        Column(
            modifier = Modifier.fillMaxWidth().padding(MaterialTheme.dimensions.fourLevel),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.threeLevel)
        ) {
            Text(
                text = originalTitle, style = MaterialTheme.typography.headlineMedium
            )

            RatingStats(
                formattedVoteCount = formattedVoteCount,
                voteAverage = voteAverage,
                isAddReviewText = true
            )

            GenresView(
                genresSeparatedByComma = genresBySeparatedByComma,
            )

            releaseDateSection()

            // This runtime is for movie
            runtime?.let {
                RuntimeView(
                    runtime = it,
                )
            }

            if (directors.isNotEmpty()) {
                DirectorSection(
                    modifier = Modifier.fillMaxWidth(),
                    directors = directors,
                    onClickedDirector = onClickedDirector
                )
            }

            OverviewTitleAndText(
                modifier = Modifier.padding(top = MaterialTheme.dimensions.twoLevel),
                overview = overview
            )

            ActorSections(
                modifier = Modifier.fillMaxWidth(),
                castOfList = castOfList,
                onClickedCastItem = onClickedCastItem
            )
        }
    }
}

@Composable
private fun RuntimeView(
    modifier: Modifier = Modifier,
    runtime: Map<String, String>,
    color: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
    style: TextStyle = MaterialTheme.typography.labelMedium
) {
    Row(modifier = modifier) {
        Icon(
            imageVector = Icons.Filled.AccessTime, contentDescription = null, tint = color
        )

        Text(
            buildAnnotatedString {
                append(runtime[hourKey])
                append("h ")
                append(runtime[minutesKey])
                append("m")
            },
            modifier = Modifier.padding(start = MaterialTheme.dimensions.oneLevel),
            style = style,
            color = color
        )

    }
}

@Composable
private fun OverviewTitleAndText(
    modifier: Modifier = Modifier, overview: String
) {
    Column(modifier = modifier) {
        Text(
            text = "Overview", style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = overview,
            modifier = Modifier.padding(top = MaterialTheme.dimensions.twoLevel),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun ActorSections(
    modifier: Modifier = Modifier,
    castOfList: List<Cast>,
    onClickedCastItem: (Int) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Actors",
            style = MaterialTheme.typography.headlineMedium
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.twoLevel)
        ) {
            items(castOfList) { cast ->
                ActorItem(
                    modifier = Modifier.clickable { onClickedCastItem(cast.id) },
                    cast = cast,
                )
            }
        }
    }
}

@Composable
private fun DirectorSection(
    modifier: Modifier = Modifier,
    directors: List<Director>,
    onClickedDirector: (Int) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Director: ",
            style = MaterialTheme.typography.headlineSmall
        )

        directors.forEach { director ->
            DirectorItem(
                modifier = Modifier.clickable { onClickedDirector(director.id) },
                director = director
            )
            Spacer(modifier = Modifier.width(MaterialTheme.dimensions.twoLevel))
        }
    }
}

@Composable
private fun DirectorItem(
    modifier: Modifier = Modifier,
    director: Director
) {
    Text(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
            .padding(
                horizontal = MaterialTheme.dimensions.twoLevel,
                vertical = MaterialTheme.dimensions.fourLevel
            ),
        text = director.name,
        color = MaterialTheme.colorScheme.onBackground
    )
}