package feature_detail.data.mapper

import feature_detail.data.dto.watchProvider.WatchProviderItemDetailDto
import feature_detail.data.dto.watchProvider.WatchProviderRegionDto
import feature_detail.data.util.Country
import feature_detail.domain.model.watchProvider.WatchProviderItem
import feature_detail.domain.model.watchProvider.WatchProviderItemInfo

fun WatchProviderRegionDto.toWatchProviderItem(
    countryIsoCode: String
): WatchProviderItem {
    val providerRegionDto = when (countryIsoCode) {
        Country.US.isoCode -> this.us
        else -> this.us
    }

    return WatchProviderItem(
        stream = providerRegionDto?.flatRate.toWatchProviderItemDetail(),
        rent = providerRegionDto?.rent.toWatchProviderItemDetail(),
        buy = providerRegionDto?.rent.toWatchProviderItemDetail()
    )
}

fun List<WatchProviderItemDetailDto>?.toWatchProviderItemDetail(): List<WatchProviderItemInfo> {
    return this?.sortedBy { it.displayPriority }?.take(2)?.map {
        WatchProviderItemInfo(
            logoPath = it.logoPath,
            providerName = it.providerName
        )
    } ?: emptyList()
}