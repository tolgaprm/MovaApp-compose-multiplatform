package feature_explore.presentation.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import core.presentation.theme.dimensions
import feature_explore.presentation.model.FilterItem

@Composable
fun ExploreSheetContent(
    modifier: Modifier = Modifier,
    categoriesFilterItems: List<FilterItem>,
    sortByFilterItems: List<FilterItem>,
    genreFilterItems: List<FilterItem>,
    onClickCategoryItem: (FilterItem) -> Unit,
    onClickGenreItem: (FilterItem) -> Unit,
    onClickSortByItem: (FilterItem) -> Unit,
    onClickResetButton: () -> Unit,
    onClickFilterApply: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimensions.fourLevel)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.fourLevel)
        ) {
            Text(
                text = "Sort & Filter",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium
            )

            Divider(modifier = Modifier.fillMaxWidth())

            SheetFilterSection(
                modifier = Modifier.fillMaxWidth(),
                title = "Categories",
                filterItems = categoriesFilterItems,
                onClick = onClickCategoryItem
            )

            SheetFilterSection(
                modifier = Modifier.fillMaxWidth(),
                title = "Genres",
                filterItems = genreFilterItems,
                onClick = onClickGenreItem
            )

            SheetFilterSection(
                modifier = Modifier.fillMaxWidth(),
                title = "Sort By",
                filterItems = sortByFilterItems,
                onClick = onClickSortByItem
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.dimensions.fourLevel)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.twoLevel)
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = onClickResetButton,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                ) {
                    Text("Reset")
                }


                Button(
                    modifier = Modifier.weight(1f),
                    onClick = onClickFilterApply,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text("Apply")
                }
            }
        }
    }
}

@Composable
private fun SheetFilterSection(
    modifier: Modifier = Modifier,
    title: String,
    filterItems: List<FilterItem>,
    onClick: (FilterItem) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.twoLevel))

        Row(
            modifier = Modifier.fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.twoLevel)
        ) {
            filterItems.forEach { filterItem ->
                MovaFilterChip(
                    filterItem = filterItem,
                    onClick = { onClick(filterItem) }
                )
            }
        }
    }
}