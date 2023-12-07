package core.data

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter

fun Int?.orZero(): Int = this ?: 0

fun Double?.orZero(): Double = this ?: 0.0

fun HttpRequestBuilder.addCommonParameters(language: String, page: Int) {
    parameter("language", language)
    parameter("page", page)
}