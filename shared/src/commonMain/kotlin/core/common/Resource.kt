package core.common

sealed class Resource<T>(val data: T? = null, val error: Throwable? = null) {
    class Success<T>(data: T) : Resource<T>(data = data)
    class Error<T>(error: Throwable, data: T? = null) : Resource<T>(data = data, error = error)
}