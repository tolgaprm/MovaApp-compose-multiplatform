package core.domain.model

enum class MediaType(val value: String) {
    MOVIE("movie"),
    TV_SERIES("tv"),
    PERSON("person");

    companion object {
        fun fromValue(value: String): MediaType =
            when (value) {
                "movie" -> MOVIE
                "tv" -> TV_SERIES
                "person" -> PERSON
                else -> MOVIE
            }
    }
}