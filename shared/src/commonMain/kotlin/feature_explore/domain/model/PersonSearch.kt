package feature_explore.domain.model

data class PersonSearch(
    val id: Int,
    val name: String,
    val personImageUrl: String?,
    val knownForDepartment: String,
)