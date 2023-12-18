package feature_explore.presentation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import feature_explore.presentation.model.FilterItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovaFilterChip(
    modifier: Modifier = Modifier,
    filterItem: FilterItem,
    onClick: () -> Unit
) {
    FilterChip(
        modifier = modifier,
        selected = filterItem.isSelected,
        onClick = onClick,
        label = {
            Text(
                text = filterItem.title,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color.Transparent,
            labelColor = MaterialTheme.colorScheme.primary,
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
        ),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = MaterialTheme.colorScheme.primary
        )
    )
}