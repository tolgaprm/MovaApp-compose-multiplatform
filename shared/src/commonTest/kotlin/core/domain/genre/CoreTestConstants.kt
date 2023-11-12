package core.domain.genre

import core.domain.genre.models.Genre

object CoreTestConstants {
    val movieEnGenreList = listOf(
        Genre(28, "Action"),
        Genre(12, "Adventure"),
        Genre(16, "Animation"),
        Genre(35, "Comedy"),
        Genre(80, "Crime")
    )

    val tvGenreList = listOf(
        Genre(10759, "Action & Adventure"),
        Genre(16, "Animation"),
        Genre(18, "Drama")
    )
}