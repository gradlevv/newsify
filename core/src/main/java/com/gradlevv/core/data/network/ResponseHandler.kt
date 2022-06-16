package com.gradlevv.core.data.network

import com.gradlevv.core.data.model.Resource
import retrofit2.Response
import timber.log.Timber


abstract class ResponseHandler() {

    protected suspend fun <T> getResource(call: suspend () -> Response<T>): Resource<T> {
        return try {
            val response: Response<T> = call()

            if (response.isSuccessful) {
                val rawResponse : T? = response.body()
                return if (rawResponse != null) {
                    Resource.Success(rawResponse)
                } else {
                    Resource.Error("Server Error")
                }
            }


            Resource.Error(response.code().toString() )
        } catch (e: Exception) {
            Timber.e(e)
            return Resource.Error(e.localizedMessage)
        }
    }

}