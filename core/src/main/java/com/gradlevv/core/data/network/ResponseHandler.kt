package com.gradlevv.core.data.network

import com.gradlevv.core.data.model.Result
import retrofit2.Response
import timber.log.Timber


abstract class ResponseHandler() {

    protected suspend fun <T> getResource(call: suspend () -> Response<T>): Result<T> {
        return try {
            val response: Response<T> = call()

            if (response.isSuccessful) {
                val rawResponse : T? = response.body()
                return if (rawResponse != null) {
                    Result.Success(rawResponse)
                } else {
                    Result.Error("Server Error")
                }
            }


            Result.Error(response.code().toString() )
        } catch (e: Exception) {
            Timber.e(e)
            return Result.Error(e.localizedMessage)
        }
    }

}