package feature_detail.domain.tv.model

enum class SeriesStatus(val value: String) {
    ENDED("Ended"),
    CONTINUE("Returning Series"),
    CANCELLED("Cancelled");

    companion object {
        fun fromValue(value: String) = entries.first {
            it.value == value
        }
    }
}