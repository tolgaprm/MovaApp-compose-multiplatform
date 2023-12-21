package feature_detail.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import core.data.util.hourKey
import core.data.util.minutesKey
import core.presentation.theme.dimensions

@Composable
fun RuntimeView(
    modifier: Modifier = Modifier,
    runtime: Map<String, String>,
    color: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
    style: TextStyle = MaterialTheme.typography.labelMedium
) {
    Row(modifier = modifier) {
        Icon(
            imageVector = Icons.Filled.AccessTime, contentDescription = null, tint = color
        )

        Text(
            buildAnnotatedString {
                append(runtime[hourKey])
                append("h ")
                append(runtime[minutesKey])
                append("m")
            },
            modifier = Modifier.padding(start = MaterialTheme.dimensions.oneLevel),
            style = style,
            color = color
        )

    }
}