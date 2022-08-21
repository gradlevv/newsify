package com.gradlevv.core.data.model

import com.gradlevv.core.mapper.BaseDataMapper


sealed class Result<out T> {
    data class Success<T>(val data: T?) : Result<T>()
    data class Error<T>(val error: String?) : Result<T>()
}

fun <From, To> Result<From>.mapTo(mapper: BaseDataMapper<From, To>): Result<To> {
    return when (val resource = this) {
        is Result.Error -> Result.Error(resource.error)
        is Result.Success -> Result.Success(resource.data?.let { mapper.mapTo(it) })
    }
}