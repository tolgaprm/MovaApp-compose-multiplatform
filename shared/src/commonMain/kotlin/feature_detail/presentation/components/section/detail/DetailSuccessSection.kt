package feature_detail.presentation.components.section.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import core.presentation.components.GenresView
import core.presentation.components.MovaImage
import core.presentation.components.RatingStats
import core.presentation.components.paging.createLazyPagingList
import core.presentation.theme.dimensions
import feature_detail.domain.model.credits.Cast
import feature_detail.domain.model.credits.Director
import feature_detail.domain.model.watchProvider.WatchProviderItem
import feature_detail.presentation.components.RuntimeView
import feature_detail.presentation.components.section.ActorSections
import feature_detail.presentation.components.section.DirectorSection
import feature_detail.presentation.components.section.WatchNowSection

@Composable
fun <T : Any> DetailSuccessSection(
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
    pagingItems: LazyPagingItems<T>,
    pagingItemComponent: @Composable (T) -> Unit,
    releaseDateSection: @Composable () -> Unit = {}
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(170.dp),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.fourLevel)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            MovaImage(
                imageUrl = posterPath,
                modifier = Modifier.fillMaxWidth().height(500.dp)
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
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

        RecommendationsSection(
            pagingItems = pagingItems,
            itemContent = { item -> pagingItemComponent(item) }
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

@Suppress("FunctionName")
private fun <T : Any> LazyGridScope.RecommendationsSection(
    pagingItems: LazyPagingItems<T>,
    itemContent: @Composable (T) -> Unit
) {
    item(span = { GridItemSpan(maxLineSpan) }) {
        Text(
            modifier = Modifier.padding(horizontal = MaterialTheme.dimensions.fourLevel),
            text = "Recommendations",
            style = MaterialTheme.typography.headlineMedium
        )
    }

    createLazyPagingList(
        pagingItems = pagingItems,
        isShowAppendLoading = false,
        itemContent = { item -> itemContent(item) }
    )
}