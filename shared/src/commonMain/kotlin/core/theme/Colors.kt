package core.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val PiercingRed = Color(0xFFE21221)
val BrilliantWhite = Color(0xFFEDF1FB)
val RiverStyx = Color(0xFF181A20)
val Salute = Color(0xFF282C36)

val lightSchemeColors = lightColorScheme(
    primary = PiercingRed,
    onPrimary = Color.White,
    background = BrilliantWhite,
    onBackground = Color.Black,
    secondaryContainer = BrilliantWhite.copy(alpha = 0.8f),
    onSecondaryContainer = Color.Black,
    tertiaryContainer = BrilliantWhite.copy(alpha = 0.8f),
    onTertiaryContainer = PiercingRed
)

val darkSchemeColors = darkColorScheme(
    primary = PiercingRed,
    onPrimary = Color.White,
    background = RiverStyx,
    onBackground = Color.White,
    secondaryContainer = Salute,
    onSecondaryContainer = Color.White,
    tertiaryContainer = Salute,
    onTertiaryContainer = PiercingRed
)