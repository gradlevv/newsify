package com.gradlevv.core.data.model

sealed class ApiError {

    object Unavailable: ApiError()
    object RateLimitError: ApiError()
    data class ServiceError(val message: String = "") : ApiError()

}