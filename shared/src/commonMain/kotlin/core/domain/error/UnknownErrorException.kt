package core.domain.error

class UnknownErrorException : Exception() {
    override val message: String
        get() = "Oops, something went wrong. Please try again later."
}