package feature_detail.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.presentation.components.GenresView
import core.presentation.components.MovaImage
import core.presentation.components.RatingStats
import core.presentation.theme.dimensions
import feature_detail.domain.model.credits.Cast
import feature_detail.domain.model.credits.Director
import feature_detail.domain.model.watchProvider.WatchProviderItem

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
    watchProviderItem: WatchProviderItem?,
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

            watchProviderItem?.let {
                WatchNowSection(
                    modifier = Modifier.fillMaxWidth(),
                    watchProviderItem = watchProviderItem
                )
            }

            ActorSections(
                modifier = Modifier.fillMaxWidth(),
                castOfList = castOfList,
                onClickedCastItem = onClickedCastItem
            )
        }
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