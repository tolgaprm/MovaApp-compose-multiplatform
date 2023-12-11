package feature_detail.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import core.data.util.hourKey
import core.data.util.minutesKey
import core.presentation.components.MovaImage
import core.presentation.components.RatingStats
import core.presentation.theme.dimensions

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
    releaseDateSection: @Composable () -> Unit = {},
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        MovaImage(
            imageUrl = posterPath, modifier = modifier.fillMaxWidth().height(500.dp)
        )
        Column(
            modifier = modifier.fillMaxWidth().padding(MaterialTheme.dimensions.fourLevel)
        ) {
            Text(
                text = originalTitle, style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimensions.twoLevel))

            RatingStats(
                modifier = modifier,
                formattedVoteCount = formattedVoteCount,
                voteAverage = voteAverage,
                isAddReviewText = true
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimensions.twoLevel))

            genresBySeparatedByComma?.let {
                GenresView(
                    modifier = modifier, genresSeparatedByComma = it
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.dimensions.twoLevel))

            releaseDateSection()

            Spacer(modifier = Modifier.height(MaterialTheme.dimensions.twoLevel))

            // This runtime is for movie
            runtime?.let {
                RuntimeView(
                    modifier = modifier,
                    runtime = it,
                )
            }

            OverviewTitleAndText(
                modifier = modifier.padding(top = MaterialTheme.dimensions.twoLevel),
                overview = overview
            )
        }
    }
}

@Composable
private fun GenresView(
    modifier: Modifier = Modifier,
    genresSeparatedByComma: String,
    textColor: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
) {
    Text(
        text = genresSeparatedByComma,
        modifier = modifier,
        style = MaterialTheme.typography.labelMedium,
        color = textColor
    )
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