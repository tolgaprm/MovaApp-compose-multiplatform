package feature_explore.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FilterAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import core.presentation.theme.dimensions

@Composable
fun ExploreScreenTopSectionWithSearchBar(
    modifier: Modifier = Modifier,
    searchText: String,
    onClickedFilter: () -> Unit,
    onSearchQueryChanged: (String) -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = MaterialTheme.dimensions.fourLevel)
            .padding(top = MaterialTheme.dimensions.fourLevel),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.twoLevel),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MovaSearchBar(
            modifier = Modifier.weight(7f),
            placeholderText = "Search for movies, tv series",
            searchText = searchText,
            onSearchTextChange = onSearchQueryChanged,
        )

        IconButton(
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .weight(1f)
                .background(MaterialTheme.colorScheme.secondaryContainer),
            onClick = onClickedFilter
        ) {
            Icon(
                imageVector = Icons.Rounded.FilterAlt,
                contentDescription = "Filter",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}