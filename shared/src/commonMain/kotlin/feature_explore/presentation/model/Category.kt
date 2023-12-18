package feature_explore.presentation.model

enum class Category(val title: String) {
    MOVIES("Movies"),
    TV_SERIES("Tv Series")
}

fun Category.toFilterItem(isSelected: Boolean): FilterItem {
    return FilterItem(
        id = ordinal,
        title = title,
        isSelected = isSelected
    )
}