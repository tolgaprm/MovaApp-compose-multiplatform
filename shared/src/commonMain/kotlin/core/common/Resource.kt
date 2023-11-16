package core.common

sealed class Resource<T>(val data: T? = null, val error: Throwable? = null) {
    class Success<T>(data: T) : Resource<T>(data = data)
    class Error<T>(error: Throwable, data: T? = null) : Resource<T>(data = data, error = error)
}

inline fun <T> Resource<T>.success(action: (T) -> Unit): Resource<T> {
    if (this is Resource.Success) {
        action(data!!)
    }
    return this
}

inline fun <T, R> Resource<T>.error(action: (Throwable) -> R): Resource<T> {
    if (this is Resource.Error) {
        action(error!!)
    }
    return this
}