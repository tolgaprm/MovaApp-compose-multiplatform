package core.data.util

import co.touchlab.kermit.Logger
import core.common.Resource

inline fun <T : Any> tryResultReturnResource(block: () -> T): Resource<T> {
    return try {
        Resource.Success(block())
    } catch (e: Exception) {
        Logger.withTag("tryResultReturnResource").e("Error: $e")
        Resource.Error(e)
    }
}