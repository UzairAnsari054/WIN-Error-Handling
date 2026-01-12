package com.example.winerrorhandling.meal_list.data.network

import com.example.winerrorhandling.core.domain.DataError
import com.example.winerrorhandling.core.domain.Result
import com.example.winerrorhandling.meal_list.data.dto.MealListResponseDto

interface RemoteMealDataSource {
    suspend fun getMeals(): Result<MealListResponseDto, DataError.Remote>
}