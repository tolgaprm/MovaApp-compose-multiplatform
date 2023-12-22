package core.common

sealed class Resource<T>(val data: T? = null, val error: Throwable? = null) {
    class Success<T>(data: T) : Resource<T>(data = data)
    class Error<T>(error: Throwable, data: T? = null) : Resource<T>(data = data, error = error)
}

inline fun <T> Resource<T>.onSuccess(action: (T) -> Unit): Resource<T> {
    if (this is Resource.Success) {
        action(data!!)
    }
    return this
}

inline fun <T, R> Resource<T>.onError(action: (Throwable) -> R): Resource<T> {
    if (this is Resource.Error) {
        action(error!!)
    }
    return this
}

inline fun <T, R> Resource<List<T>>.mapResourceToList(transform: (T) -> R): List<R> {
    return when (this) {
        is Resource.Success -> data?.map(transform) ?: emptyList()
        is Resource.Error -> emptyList()
    }
}