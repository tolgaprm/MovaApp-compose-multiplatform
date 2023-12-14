package feature_upcoming.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import core.domain.movie.Movie
import core.presentation.components.InfoBottomSheet
import core.presentation.components.paging.MPagingColumnList
import core.presentation.theme.dimensions
import feature_upcoming.presentation.components.UpComingMovieItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpComingScreen(
    modifier: Modifier = Modifier,
    pagingMovies: LazyPagingItems<Movie>?,
    uiState: UpComingUiState,
    navigateToDetailScreen: () -> Unit,
    onEvent: (UpComingScreenEvent) -> Unit
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            skipHiddenState = false
        )
    )
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        modifier = modifier.fillMaxSize(),
        sheetPeekHeight = 0.dp,
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            InfoBottomSheet(
                modifier = Modifier.fillMaxWidth(),
                selectedMovie = uiState.selectedMovie,
                selectedTvSeries = null,
                onClickClose = {
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.hide()
                    }
                },
                onClickedDetails = navigateToDetailScreen
            )
        },
        sheetContainerColor = MaterialTheme.colorScheme.background,
        sheetContentColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            Text(
                modifier = Modifier.padding(MaterialTheme.dimensions.fourLevel),
                text = "Coming Soon",
                style = MaterialTheme.typography.headlineMedium,
            )
        }
    ) {
        MPagingColumnList(
            modifier = Modifier.padding(top = it.calculateTopPadding()).fillMaxSize(),
            pagingItems = pagingMovies,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.fourLevel),
            paddingValues = PaddingValues(MaterialTheme.dimensions.fourLevel)
        ) { movie ->
            UpComingMovieItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { },
                movie = movie,
                onClickedInfo = {
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.expand()
                        onEvent(UpComingScreenEvent.OnClickInfo(movie = movie))
                    }
                }
            )
        }
    }
}