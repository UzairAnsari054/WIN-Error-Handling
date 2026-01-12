package com.example.winerrorhandling.meal_list.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealListResponseDto(
    @SerialName("categories")val results: List<MealDto>
)
