package feature_detail.presentation.components.section.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import app.cash.paging.compose.LazyPagingItems
import core.domain.model.tv.TvSeries
import core.presentation.theme.dimensions
import feature_detail.domain.model.tv.TvSeriesDetail
import feature_detail.presentation.components.recommendations.TvSeriesRecommendationItem

@Composable
fun TvDetailSuccessView(
    modifier: Modifier = Modifier,
    tvSeriesDetail: TvSeriesDetail,
    tvSeriesRecommendations: LazyPagingItems<TvSeries>,
    onClickedCastItem: (Int) -> Unit,
    onClickedDirector: (Int) -> Unit,
    onClickedRecommendationItem: (TvSeries) -> Unit
) {
    DetailSuccessSection(
        modifier = modifier,
        posterPath = tvSeriesDetail.posterPath,
        originalTitle = tvSeriesDetail.name,
        formattedVoteCount = tvSeriesDetail.formattedVoteCount,
        voteAverage = tvSeriesDetail.voteAverage,
        genresBySeparatedByComma = tvSeriesDetail.genresBySeparatedByComma,
        overview = tvSeriesDetail.overview,
        releaseDateSection = {
            TvSeriesReleaseDateAndSeasonNumber(
                modifier = modifier,
                firstAirDate = tvSeriesDetail.releaseDate,
                lastAirDate = tvSeriesDetail.lastAirDate,
                numberOfSeason = tvSeriesDetail.numberOfSeasons,
                inProduction = tvSeriesDetail.inProduction
            )
        },
        castOfList = tvSeriesDetail.cast,
        directors = tvSeriesDetail.directors,
        onClickedCastItem = onClickedCastItem,
        onClickedDirector = onClickedDirector,
        watchProviderItem = tvSeriesDetail.watchProviderItem,
        pagingItems = tvSeriesRecommendations,
        pagingItemComponent = { tvSeries ->
            TvSeriesRecommendationItem(
                modifier = Modifier.padding(horizontal = MaterialTheme.dimensions.fourLevel)
                    .clickable { onClickedRecommendationItem(tvSeries) },
                tvSeries = tvSeries
            )
        }
    )
}

@Composable
private fun TvSeriesReleaseDateAndSeasonNumber(
    modifier: Modifier = Modifier,
    firstAirDate: String,
    lastAirDate: String?,
    numberOfSeason: Int?,
    inProduction: Boolean
) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            append(firstAirDate)
            append(" - ")
            if (!inProduction) {
                lastAirDate?.let {
                    append(lastAirDate)
                }
            } else {
                append("Continuing")
            }

            withStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                )
            ) {
                append(" * ")
            }

            withStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                )
            ) {
                append(" ${numberOfSeason ?: 0} Seasons")
            }
        },
        style = MaterialTheme.typography.labelMedium,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
    )
}