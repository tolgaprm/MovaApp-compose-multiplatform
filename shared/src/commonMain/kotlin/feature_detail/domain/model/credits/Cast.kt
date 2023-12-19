package feature_detail.domain.model.credits

data class Cast(
    val id: Int,
    val name: String,
    val originalName: String,
    val profilePath: String?,
    val character: String,
)