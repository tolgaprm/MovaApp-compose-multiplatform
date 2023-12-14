package core.presentation.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Modifier.verticalGradientRect(): Modifier {
    return drawBehind {
        drawRoundRect(
            brush = Brush.verticalGradient(
                listOf(
                    Color.Black.copy(alpha = 0.4f),
                    Color.Black.copy(alpha = 0.6f),
                    Color.Black.copy(alpha = 0.8f)
                )
            )
        )
    }
}