package feature_detail.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.presentation.components.MovaImage
import core.presentation.theme.dimensions
import feature_detail.domain.model.watchProvider.WatchProviderItem
import feature_detail.domain.model.watchProvider.WatchProviderItemInfo

@Composable
fun WatchNowSection(
    modifier: Modifier = Modifier,
    watchProviderItem: WatchProviderItem
) {
    Column(modifier = modifier) {
        Text(
            text = "Watch Now",
            style = MaterialTheme.typography.headlineMedium
        )
        WatchProviderSection(
            modifier = Modifier.fillMaxWidth()
                .padding(top = MaterialTheme.dimensions.twoLevel),
            watchProviderItem = watchProviderItem
        )
    }
}

@Composable
private fun WatchProviderSection(
    modifier: Modifier = Modifier,
    watchProviderItem: WatchProviderItem
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.fourLevel)
    ) {
        WatchProviderItemRowList(
            modifier = Modifier.fillMaxWidth(),
            watchProviderItems = watchProviderItem.stream,
            title = "Stream"
        )

        WatchProviderItemRowList(
            modifier = Modifier.fillMaxWidth(),
            watchProviderItems = watchProviderItem.rent,
            title = "Rent"
        )

        WatchProviderItemRowList(
            modifier = Modifier.fillMaxWidth(),
            watchProviderItems = watchProviderItem.buy,
            title = "Buy"
        )
    }
}

@Composable
private fun WatchProviderItemRowList(
    modifier: Modifier = Modifier,
    title: String,
    watchProviderItems: List<WatchProviderItemInfo>
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                fontSize = 18.sp,
                fontFamily = FontFamily.Monospace
            )
        )

        Spacer(modifier = Modifier.width(MaterialTheme.dimensions.fourLevel))

        if (watchProviderItems.isNotEmpty()) {
            watchProviderItems.forEach { watchProviderItemInfo ->
                MovaImage(
                    imageUrl = watchProviderItemInfo.logoPath,
                    modifier = Modifier.size(50.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentDescription = watchProviderItemInfo.providerName
                )
                Spacer(modifier = Modifier.width(MaterialTheme.dimensions.fourLevel))
            }
        } else {
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = null,
                modifier = Modifier.size(50.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}