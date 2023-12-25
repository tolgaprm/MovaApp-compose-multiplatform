package core.data.util

import kotlinx.datetime.LocalDate

object DateTimeUtil {

    fun convertDateFormat(inputDate: String?): String? {
        if (inputDate == null) return null

        val dateFormat = LocalDate.parse(inputDate)

        val year = dateFormat.year
        val month = dateFormat.month.name.lowercase().replaceFirstChar { it.uppercase() }
        val day = dateFormat.dayOfMonth

        return "$day $month, $year"
    }
}