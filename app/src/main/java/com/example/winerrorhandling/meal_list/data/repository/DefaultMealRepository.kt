package com.example.winerrorhandling.meal_list.data.repository

import com.example.winerrorhandling.core.domain.DataError
import com.example.winerrorhandling.core.domain.Result
import com.example.winerrorhandling.core.domain.map
import com.example.winerrorhandling.meal_list.data.mappers.toMeal
import com.example.winerrorhandling.meal_list.data.network.RemoteMealDataSource
import com.example.winerrorhandling.meal_list.domain.Meal
import com.example.winerrorhandling.meal_list.domain.MealRepository

class DefaultMealRepository(
    private val remoteMealDataSource: RemoteMealDataSource
): MealRepository {
    override suspend fun getMeals(): Result<List<Meal>, DataError.Remote> {
        return remoteMealDataSource
            .getMeals()
            .map { dto ->
                dto.results.map { it.toMeal() }
            }
    }
}