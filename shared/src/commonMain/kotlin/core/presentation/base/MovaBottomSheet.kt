package core.presentation.base

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import core.domain.movie.Movie
import core.domain.tvseries.TvSeries
import core.presentation.components.InfoBottomSheet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovaOptionalInfoBottomSheetScaffold(
    modifier: Modifier = Modifier,
    sheetPeekHeight: Dp = 0.dp,
    sheetContainerColor: Color = MaterialTheme.colorScheme.background,
    sheetContentColor: Color = MaterialTheme.colorScheme.onBackground,
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            skipHiddenState = false
        )
    ),
    selectedMovie: Movie? = null,
    selectedTvSeries: TvSeries? = null,
    isShowInfoBottomSheet: Boolean = selectedMovie != null || selectedTvSeries != null,
    onClickCloseBottomSheet: () -> Unit,
    onClickedDetails: () -> Unit,
    topBar: @Composable (() -> Unit)? = null,
    otherSheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        sheetContent = {
            if (isShowInfoBottomSheet) {
                InfoBottomSheet(
                    modifier = Modifier.fillMaxWidth(),
                    selectedMovie = selectedMovie,
                    selectedTvSeries = selectedTvSeries,
                    onClickClose = onClickCloseBottomSheet,
                    onClickedDetails = onClickedDetails
                )
            } else {
                otherSheetContent()
            }
        },
        sheetPeekHeight = sheetPeekHeight,
        topBar = topBar,
        sheetContainerColor = sheetContainerColor,
        sheetContentColor = sheetContentColor,
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovaInfoBottomSheetScaffold(
    modifier: Modifier = Modifier,
    sheetPeekHeight: Dp = 0.dp,
    sheetContainerColor: Color = MaterialTheme.colorScheme.background,
    sheetContentColor: Color = MaterialTheme.colorScheme.onBackground,
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            skipHiddenState = false
        )
    ),
    selectedMovie: Movie? = null,
    selectedTvSeries: TvSeries? = null,
    onClickCloseBottomSheet: () -> Unit,
    onClickedDetails: () -> Unit,
    topBar: @Composable (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        sheetContent = {
            InfoBottomSheet(
                modifier = Modifier.fillMaxWidth(),
                selectedMovie = selectedMovie,
                selectedTvSeries = selectedTvSeries,
                onClickClose = onClickCloseBottomSheet,
                onClickedDetails = onClickedDetails
            )
        },
        sheetPeekHeight = sheetPeekHeight,
        topBar = topBar,
        sheetContainerColor = sheetContainerColor,
        sheetContentColor = sheetContentColor,
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
fun CoroutineScope.expandBottomSheet(bottomSheetScaffoldState: BottomSheetScaffoldState) {
    launch {
        bottomSheetScaffoldState.bottomSheetState.expand()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun CoroutineScope.hideBottomSheet(bottomSheetScaffoldState: BottomSheetScaffoldState) {
    launch {
        bottomSheetScaffoldState.bottomSheetState.hide()
    }
}