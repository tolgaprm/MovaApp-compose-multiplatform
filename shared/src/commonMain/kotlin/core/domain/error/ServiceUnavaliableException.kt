package core.domain.error

class ServiceUnavailableException : Exception() {
    override val message: String
        get() = "Oops, something went wrong. Please check your internet connection and try again later."
}