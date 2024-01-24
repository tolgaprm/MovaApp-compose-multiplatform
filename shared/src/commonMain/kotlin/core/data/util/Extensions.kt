package core.data.util

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter

fun Int?.orZero(): Int = this ?: 0

fun Double?.orZero(): Double = this ?: 0.0

fun HttpRequestBuilder.addCommonParameters(language: String, page: Int) {
    addLanguageParameter(language = language)
    addPageParameter(page = page)
}

fun HttpRequestBuilder.addAppendToResponseQuery(
    appendToResponses: List<String>
) {
    parameter(APPEND_TO_RESPONSE_QUERY, appendToResponses.joinToString(","))
}

fun HttpRequestBuilder.addLanguageParameter(language: String) {
    parameter(LANGUAGE_PARAM, language)
}

fun HttpRequestBuilder.addPageParameter(page: Int) {
    parameter(PAGE_PARAM, page)
}

fun Boolean?.orFalse(): Boolean = this ?: false

private const val LANGUAGE_PARAM = "language"
private const val PAGE_PARAM = "page"
private const val APPEND_TO_RESPONSE_QUERY = "append_to_response"