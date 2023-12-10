package core.data.util

import co.touchlab.kermit.Logger
import core.domain.error.ServiceUnavailableException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.errors.IOException
import kotlin.coroutines.cancellation.CancellationException

@Throws(ServiceUnavailableException::class, CancellationException::class)
suspend inline fun <reified R> tryResult(
    block: () -> HttpResponse
): R {

    val result = try {
        block()
    } catch (e: IOException) {
        Logger.withTag("tryResult").e("IOException ${e.message}")
        throw ServiceUnavailableException()
    }

    when (result.status.value) {
        in 200..299 -> Unit
        500 -> throw ServiceUnavailableException()
        in 400..499 -> throw ServiceUnavailableException()
        else -> throw ServiceUnavailableException()
    }

    return try {
        result.body<R>()
    } catch (e: Exception) {
        Logger.withTag("tryResult").e("Exception ${e.message}")
        throw ServiceUnavailableException()
    }
}