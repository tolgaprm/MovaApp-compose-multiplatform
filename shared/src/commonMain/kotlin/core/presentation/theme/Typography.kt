package core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val FontFamily.Companion.MonospaceFont: FontFamily
    get() = Monospace

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily.MonospaceFont,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.MonospaceFont,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily.MonospaceFont,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.MonospaceFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.MonospaceFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.MonospaceFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    )
)