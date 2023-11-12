package core.data.util

import core.domain.error.ServiceUnavailableException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.errors.IOException

suspend inline fun <reified R> tryResult(
    block: () -> HttpResponse
): R {

    val result = try {
        block()
    } catch (e: IOException) {
        println("Mova: IOException ${e.message}")
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
        throw ServiceUnavailableException()
    }
}