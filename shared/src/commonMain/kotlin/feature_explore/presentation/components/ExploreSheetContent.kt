package feature_explore.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import core.presentation.theme.dimensions
import feature_explore.presentation.model.FilterItem

@Composable
fun ExploreSheetContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimensions.fourLevel)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Sort & Filter",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimensions.fourLevel))

            SheetFilterSection(
                modifier = Modifier.fillMaxWidth(),
                title = "Categories",
                filterItems = listOf(
                    FilterItem(
                        id = 1,
                        title = "Movies",
                        isSelected = true
                    ),
                    FilterItem(
                        id = 2,
                        title = "Tv Series",
                        isSelected = false
                    )
                )
            )
        }
    }
}

@Composable
private fun SheetFilterSection(
    modifier: Modifier = Modifier,
    title: String,
    filterItems: List<FilterItem>,
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.twoLevel))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            filterItems.forEach { filterItem ->
                MovaFilterChip(
                    filterItem = filterItem
                )
            }
        }
    }
}