package feature_explore.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FilterAlt
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import core.presentation.theme.dimensions
import feature_explore.presentation.components.ExploreSheetContent
import feature_explore.presentation.components.MovaSearchBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    uiState: ExploreScreenUiState,
    onEvent: (ExploreScreenEvent) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            skipHiddenState = false
        )
    )

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = { ExploreSheetContent() },
        sheetPeekHeight = 0.dp,
        sheetContainerColor = MaterialTheme.colorScheme.background,
        sheetContentColor = MaterialTheme.colorScheme.onBackground
    ) {
        ExploreScreenContent(
            modifier = Modifier.fillMaxSize(),
            uiState = uiState,
            onEvent = onEvent,
            onClickedFilter = {
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                }
            }
        )
    }
}

@Composable
private fun ExploreScreenContent(
    modifier: Modifier = Modifier,
    uiState: ExploreScreenUiState,
    onEvent: (ExploreScreenEvent) -> Unit,
    onClickedFilter: () -> Unit
) {
    Box(modifier = modifier) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = MaterialTheme.dimensions.fourLevel)
                    .padding(top = MaterialTheme.dimensions.fourLevel),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.twoLevel),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MovaSearchBar(
                    modifier = Modifier.weight(7f),
                    placeholderText = "Search for movies, tv series",
                    searchText = uiState.searchText,
                    onSearchTextChange = {
                        onEvent(ExploreScreenEvent.OnSearchTextChanged(it))
                    }
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
    }
}