package core.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import co.touchlab.kermit.Logger
import core.common.ImageSize
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.http.Url

private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"

@Composable
fun MovaImage(
    imageUrl: String?,
    imageSize: ImageSize = ImageSize.ORIGINAL,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null,
    isShowLoading: Boolean = true,
    modifier: Modifier = Modifier,
    addOnFailureItem: () -> Unit = {},
) {
    imageUrl?.let {
        KamelImage(
            modifier = modifier,
            resource = asyncPainterResource(data = Url(getImageUrl(imageUrl, imageSize))),
            onFailure = { error ->
                // Handle failure
                Logger.withTag("Image Loading Kamel").a("Error loading image: $error")
            },
            onLoading = {
                if (isShowLoading) {
                    MCircularProgressIndicator()
                }
            },
            contentDescription = contentDescription,
            contentScale = contentScale
        )
    } ?: return
}

private fun getImageUrl(imageUrl: String, imageSize: ImageSize): String {
    return "$IMAGE_BASE_URL/${imageSize.path}/$imageUrl"
}