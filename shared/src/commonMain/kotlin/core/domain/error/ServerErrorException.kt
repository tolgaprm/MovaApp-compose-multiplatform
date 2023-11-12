package core.domain.error

class ServerErrorException : Exception() {
    override val message: String
        get() = "Oops, something went wrong. Please try again later."
}