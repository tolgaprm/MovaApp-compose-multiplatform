package feature_detail.presentation.components.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import core.presentation.components.MovaImage
import core.presentation.theme.dimensions
import feature_detail.domain.model.credits.Cast

@Composable
fun ActorSections(
    modifier: Modifier = Modifier,
    castOfList: List<Cast>,
    onClickedCastItem: (Int) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Actors",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.twoLevel))

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
private fun ActorItem(
    modifier: Modifier = Modifier,
    cast: Cast
) {
    Row(
        modifier = modifier
            .heightIn(min = 90.dp)
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(MaterialTheme.dimensions.twoLevel),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.twoLevel)
    ) {
        MovaImage(
            modifier = Modifier
                .size(80.dp)
                .clip(MaterialTheme.shapes.medium),
            imageUrl = cast.profilePath
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.twoLevel)
        ) {
            Text(
                text = cast.name,
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = cast.character,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}