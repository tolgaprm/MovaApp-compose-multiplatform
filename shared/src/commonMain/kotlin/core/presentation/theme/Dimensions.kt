package core.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val oneLevel: Dp = 4.dp,
    val twoLevel: Dp = 8.dp,
    val threeLevel: Dp = 12.dp,
    val fourLevel: Dp = 16.dp,
    val fiveLevel: Dp = 20.dp,
    val sixLevel: Dp = 24.dp,
    val sevenLevel: Dp = 28.dp,
    val eightLevel: Dp = 32.dp
)

val LocalDimensions = staticCompositionLocalOf { Dimensions() }

val MaterialTheme.dimensions: Dimensions
    @Composable
    @ReadOnlyComposable
    get() = LocalDimensions.current