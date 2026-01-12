package com.example.winerrorhandling.meal_list.data.network

import com.example.winerrorhandling.core.data.safeCall
import com.example.winerrorhandling.core.domain.DataError
import com.example.winerrorhandling.core.domain.Result
import com.example.winerrorhandling.meal_list.data.dto.MealListResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val BASE_URL = "https://themealdb.com/api/json/v1/1"

class KtorRemoteMealDataSource(
    private val httpClient: HttpClient
) : RemoteMealDataSource {
    override suspend fun getMeals(): Result<MealListResponseDto, DataError.Remote> {
//        return safeCall {
//            httpClient.get(
//                urlString = "$BASE_URL/categories.php"
//            )
//        }

        return safeCall(
            customErrorMapping = { code, _ ->
                when (code) {
                    409 -> DataError.Remote.PARTNER_ALREADY_EXISTS
                    410 -> DataError.Remote.ALREADY_IN_WISHLIST
                    else -> null
                }
            },
            execute = { 410 } // CONTROL WITH THIS
        )
    }
}