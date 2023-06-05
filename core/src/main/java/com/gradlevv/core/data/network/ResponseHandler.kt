package com.gradlevv.core.data.network

import com.gradlevv.core.data.model.ApiError
import com.gradlevv.core.data.model.ErrorHandler.errorParser
import com.gradlevv.core.data.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import retrofit2.Response
import timber.log.Timber


abstract class ResponseHandler {

    protected suspend fun <T> getResource(call: suspend () -> Response<T>): Result<T> {
        return try {
            val response: Response<T> = call()

            if (response.isSuccessful) {
                val rawResponse: T? = response.body()
                return if (rawResponse != null) {
                    Result.Success(rawResponse)
                } else {
                    Result.Error(ApiError.ServiceError(message = response.message()))
                }
            }

            Result.Error(errorParser(response.code()))
        } catch (e: Exception) {
            Timber.e(e)
            return Result.Error(ApiError.Unavailable)
        }
    }

    protected fun <T> getResultFlow(call: suspend () -> Response<T>): Flow<Result<T>> {
        return flow {
            try {
                val response: Response<T> = call()

                if (response.isSuccessful) {
                    val rawResponse: T? = response.body()
                    if (rawResponse != null) {
                        emit(Result.Success(rawResponse))
                    } else {
                        emit(Result.Error(ApiError.ServiceError(message = response.message())))
                    }
                } else {
                    emit(Result.Error(errorParser(response.code())))
                }

            } catch (e: Exception) {
                Timber.e(e)
                emit(Result.Error(ApiError.Unavailable))
            }
        }.catch {
            emit(Result.Error(ApiError.Unavailable))
        }
    }
}