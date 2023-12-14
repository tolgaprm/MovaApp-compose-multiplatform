package feature_explore.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import core.presentation.theme.dimensions
import feature_explore.presentation.model.SearchItemType

@Composable
fun SearchItemTypeText(
    modifier: Modifier = Modifier,
    searchItemType: SearchItemType
) {
    Text(
        text = searchItemType.value,
        modifier = modifier
            .padding(MaterialTheme.dimensions.twoLevel)
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(MaterialTheme.dimensions.twoLevel)
    )
}