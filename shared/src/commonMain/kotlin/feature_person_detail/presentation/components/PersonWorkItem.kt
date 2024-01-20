package feature_person_detail.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import core.domain.model.MediaType
import core.presentation.components.MovaImage
import core.presentation.theme.dimensions

@Composable
fun PersonWorkItem(
    modifier: Modifier = Modifier,
    posterImageUrl: String?,
    mediaType: MediaType,
    title: String,
    subtitle: String,
) {
    Column(
        modifier = modifier.width(165.dp),
    ) {
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {
            Box {
                MovaImage(
                    imageUrl = posterImageUrl,
                    modifier = Modifier.fillMaxWidth()
                        .height(250.dp)
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                )

                Text(
                    text = mediaType.value.replaceFirstChar { it.uppercase() },
                    modifier = modifier
                        .align(Alignment.TopEnd)
                        .padding(MaterialTheme.dimensions.twoLevel)
                        .clip(MaterialTheme.shapes.small)
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .padding(MaterialTheme.dimensions.twoLevel)
                )
            }
        }

        Text(
            text = title,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
        )

        Text(
            text = subtitle,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
        )
    }
}