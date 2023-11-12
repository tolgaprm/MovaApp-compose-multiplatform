package core.domain.error

class ClientErrorException : Exception() {
    override val message: String
        get() = "Oops, an error happened. Please contact the support."
}