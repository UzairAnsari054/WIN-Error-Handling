package com.example.winerrorhandling.meal_list.domain

import com.example.winerrorhandling.core.domain.DataError
import com.example.winerrorhandling.core.domain.Result

interface MealRepository {
    suspend fun getMeals(): Result<List<Meal>, DataError.Remote>
}