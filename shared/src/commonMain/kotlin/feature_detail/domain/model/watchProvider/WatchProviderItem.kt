package feature_detail.domain.model.watchProvider

data class WatchProviderItem(
    val stream: List<WatchProviderItemDetail>,
    val rent: List<WatchProviderItemDetail>,
    val buy: List<WatchProviderItemDetail>
)

data class WatchProviderItemDetail(
    val logoPath: String,
    val providerName: String,
)