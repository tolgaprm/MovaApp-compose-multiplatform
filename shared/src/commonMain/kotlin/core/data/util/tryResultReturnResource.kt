package core.data.util

import core.common.Resource

inline fun <T : Any> tryResultReturnResource(block: () -> T): Resource<T> {
    return try {
        Resource.Success(block())
    } catch (e: Exception) {
        Resource.Error(e)
    }
}