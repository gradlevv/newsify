package com.gradlevv.core.data.model


sealed class Resource<out T>() {
    data class Success<T>(val data: T?) : Resource<T>()
    data class Error<T>(val error: String?) : Resource<T>()
}
