package feature_explore.presentation.model

data class FilterItem(
    val id: Int,
    val title: String,
    val isSelected: Boolean
)

fun List<FilterItem>.joinWithComma(): String {
    return this.joinToString(",") { it.id.toString() }
}