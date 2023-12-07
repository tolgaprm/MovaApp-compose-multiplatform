package feature_detail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen

data class DetailScreenRoute(
    val mediaId: Int? = null
) : Screen {

    @Composable
    override fun Content() {
        DetailScreen(
            mediaId = mediaId
        )
    }
}

@Composable
private fun DetailScreen(
    modifier: Modifier = Modifier,
    mediaId: Int?
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Detail Screen $mediaId")
    }
}