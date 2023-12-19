package feature_detail.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun ActorItem(
    modifier: Modifier = Modifier,
    cast: Cast
) {
    Row(
        modifier = modifier
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