package com.gradlevv.core.data.model

import com.gradlevv.core.mapper.BaseDataMapper


sealed class Resource<out T> {
    data class Success<T>(val data: T?) : Resource<T>()
    data class Error<T>(val error: String?) : Resource<T>()
}

fun <From, To> Resource<From>.mapTo(mapper: BaseDataMapper<From, To>): Resource<To> {
    return when (val resource = this) {
        is Resource.Error -> Resource.Error(resource.error)
        is Resource.Success -> Resource.Success(resource.data?.let { mapper.mapTo(it) })
    }
}