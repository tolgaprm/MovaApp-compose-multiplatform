package feature_explore.presentation.model

enum class SortBy(val title: String, val queryParam: String) {
    POPULARITY("Popularity", "popularity.desc"),
    LATEST_RELEASE("Latest Release", "primary_release_date.asc")
}

fun SortBy.toFilterItem(isSelected: Boolean): FilterItem {
    return FilterItem(
        id = ordinal,
        title = title,
        isSelected = isSelected
    )
}