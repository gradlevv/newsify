package com.gradlevv.core.data.model


sealed class Result<out T> {
    data class Success<T>(val data: T?) : Result<T>()
    data class Error<T>(val error: ApiError?) : Result<T>()
}

inline fun <From, To> Result<From>.map(transform: (From) -> To): Result<To> {
    return when (this) {
        is Result.Success -> Result.Success(data?.let(transform))
        is Result.Error -> Result.Error(error)
    }
}