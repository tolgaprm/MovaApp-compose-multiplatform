package core.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RatingStats(
    modifier: Modifier = Modifier,
    formattedVoteCount: String,
    voteAverage: Double
) {
    Row(
        modifier = modifier,
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            tint = Color.Yellow
        )
        Text(
            text = "$voteAverage ($formattedVoteCount)",
            modifier = Modifier.padding(start = 4.dp),
        )
    }
}