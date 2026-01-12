package com.example.winerrorhandling.meal_list.presentation

import com.example.winerrorhandling.core.presentation.UiText
import com.example.winerrorhandling.meal_list.domain.Meal

data class MealListState(
    val isLoading: Boolean = true,
    val errorMessage: UiText? = null,
    val meals: List<Meal> = emptyList(),
)