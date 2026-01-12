package com.example.winerrorhandling.meal_list.data.mappers

import com.example.winerrorhandling.meal_list.data.dto.MealDto
import com.example.winerrorhandling.meal_list.domain.Meal

fun MealDto.toMeal(): Meal {
    return Meal(
        id = idCategory,
        name = strCategory,
        category = strCategoryDescription,
    )
}