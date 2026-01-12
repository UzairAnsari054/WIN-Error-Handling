package com.example.winerrorhandling.core.data

import com.example.winerrorhandling.core.domain.DataError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import com.example.winerrorhandling.core.domain.Result
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
//    execute: () -> HttpResponse
    execute: () -> Int,
    noinline customErrorMapping: ((Int, String?) -> DataError.Remote?)? = null
): Result<T, DataError.Remote> {
    val response = try {
        execute()
    } catch (e: SocketTimeoutException) {
        return Result.Error(DataError.Remote.REQUEST_TIMEOUT)
    } catch (e: UnresolvedAddressException) {
        return Result.Error(DataError.Remote.NO_INTERNET)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(DataError.Remote.UNKNOWN)
    }

    return responseToResult(response, customErrorMapping)
}

suspend inline fun <reified T> responseToResult(
//    response: HttpResponse
    response: Int,
    noinline customErrorMapping: ((Int, String?) -> DataError.Remote?)? = null
): Result<T, DataError.Remote> {
//    val status = response.status.value
    val status = response

    if (status in 200..299) {
        return try {
//            Result.Success(response.body<T>())
            Result.Error(DataError.Remote.SUCCESS)
        } catch (e: NoTransformationFoundException) {
            Result.Error(DataError.Remote.SERIALIZATION)
        }
    }

    if (customErrorMapping != null) {
//        val errorBody = try { response.bodyAsText() } catch (e: Exception) { null }
        val errorBody = try { "response PODAAA" } catch (e: Exception) { null }

        val customError = customErrorMapping(status, errorBody)
        if (customError != null) {
            return Result.Error(customError)
        }
    }

    return when (response) {
        408 -> Result.Error(DataError.Remote.REQUEST_TIMEOUT)
        429 -> Result.Error(DataError.Remote.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(DataError.Remote.SERVER)
        else -> Result.Error(DataError.Remote.UNKNOWN)
    }
}