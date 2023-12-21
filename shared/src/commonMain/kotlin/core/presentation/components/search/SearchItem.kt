package core.presentation.components.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.presentation.components.MovaImage
import core.presentation.components.RatingStats
import core.presentation.components.verticalGradientRect
import core.presentation.theme.dimensions

@Composable
fun SearchItem(
    modifier: Modifier = Modifier,
    posterImageUrl: String?,
    title: String,
    formattedVoteCount: String,
    voteAverage: Double,
    searchItemType: SearchItemType
) {
    Card(
        modifier = modifier.width(165.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            MovaImage(
                imageUrl = posterImageUrl,
                modifier = Modifier.fillMaxWidth()
                    .height(350.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
            )

            SearchItemTypeText(
                modifier = Modifier.align(Alignment.TopEnd),
                searchItemType = searchItemType
            )

            Column(
                modifier = Modifier
                    .verticalGradientRect()
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(MaterialTheme.dimensions.fourLevel),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.twoLevel)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )

                RatingStats(
                    formattedVoteCount = formattedVoteCount,
                    voteAverage = voteAverage,
                    textColor = Color.White
                )
            }
        }
    }
}