package core.data

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter

fun Int?.orZero(): Int = this ?: 0

fun Double?.orZero(): Double = this ?: 0.0

fun HttpRequestBuilder.addCommonParameters(language: String, page: Int) {
    addLanguageParameter(language = language)
    addPageParameter(page = page)
}

fun HttpRequestBuilder.addLanguageParameter(language: String) {
    parameter(LANGUAGE_PARAM, language)
}

fun HttpRequestBuilder.addPageParameter(page: Int) {
    parameter(PAGE_PARAM, page)
}

fun Boolean?.orFalse(): Boolean = this ?: false

const val LANGUAGE_PARAM = "language"
const val PAGE_PARAM = "page"