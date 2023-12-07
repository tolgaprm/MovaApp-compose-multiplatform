package core.domain.util

import core.domain.genre.models.Genre

object MovaUtil {
    fun getGenresBySeparatedByComma(genreIds: List<Int>, genres: List<Genre>?): String {
        if (genres == null) return ""
        return genreIds.joinToString(", ") { genreId ->
            genres.find { genre -> genre.id == genreId }?.name ?: ""
        }
    }

    fun formatVoteCount(voteCount: Int?): String {
        if (voteCount == null) return ""
        if (voteCount < 1000) return voteCount.toString()
        val voteAverageInThousand = voteCount / 1000
        return "${voteAverageInThousand}k"
    }

    fun getYearFromReleaseDate(releaseDate: String?): String {
        if (releaseDate == null) return ""
        return releaseDate.split("-").first()
    }
}