package core.domain.util

import core.domain.genre.models.Genre

object GenreUtil {
    fun getGenresBySeparatedByComma(genreIds: List<Int>, genres: List<Genre>?): String {
        if (genres == null) return ""
        return genreIds.joinToString(", ") { genreId ->
            genres.find { genre -> genre.id == genreId }?.name ?: ""
        }
    }
}