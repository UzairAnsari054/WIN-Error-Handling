package com.example.winerrorhandling.meal_list.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MealDto(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
)
