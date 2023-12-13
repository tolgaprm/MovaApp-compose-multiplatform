package feature_explore.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

@Composable
fun MovaSearchBar(
    modifier: Modifier = Modifier,
    placeholderText: String,
    searchText: String,
    onSearchTextChange: (String) -> Unit
) {
    TextField(
        modifier = modifier
            .clip(MaterialTheme.shapes.large),
        value = searchText,
        onValueChange = onSearchTextChange,
        placeholder = {
            Text(text = placeholderText)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "Search"
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    )
}