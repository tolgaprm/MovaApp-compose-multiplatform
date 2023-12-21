package feature_detail.presentation.components.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import core.presentation.theme.dimensions
import feature_detail.domain.model.credits.Director

@Composable
fun DirectorSection(
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