package core.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun GenresView(
    modifier: Modifier = Modifier,
    genresSeparatedByComma: String?,
    textColor: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
) {
    genresSeparatedByComma ?: return
    Text(
        text = genresSeparatedByComma,
        modifier = modifier,
        style = MaterialTheme.typography.labelMedium,
        color = textColor
    )
}