package core.data.util

import kotlinx.datetime.LocalDate

object DateTimeUtil {

    fun convertDateFormat(inputDate: String?): String {
        if (inputDate == null) return ""

        val dateFormat = LocalDate.parse(inputDate)

        val year = dateFormat.year
        val month = dateFormat.month.name
        val day = dateFormat.dayOfMonth

        return "$day $month, $year"
    }
}