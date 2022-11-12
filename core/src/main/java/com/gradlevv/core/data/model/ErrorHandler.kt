package com.gradlevv.core.data.model

object ErrorHandler {

    @JvmStatic
    fun errorParser(code: Int): ApiError {
        return when (code) {
            429 -> ApiError.RateLimitError
            else -> ApiError.Unavailable
        }

    }

}