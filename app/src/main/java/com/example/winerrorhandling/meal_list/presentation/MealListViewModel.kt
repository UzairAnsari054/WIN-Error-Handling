package com.example.winerrorhandling.meal_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.winerrorhandling.core.domain.onError
import com.example.winerrorhandling.core.domain.onSuccess
import com.example.winerrorhandling.core.presentation.toUiText
import com.example.winerrorhandling.meal_list.domain.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MealListViewModel(
    private val mealRepository: MealRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MealListState())
    val state = _state.asStateFlow()

    init {
        getMeals()
    }

    fun getMeals() = viewModelScope.launch {
        _state.update {
            it.copy(
                isLoading = true
            )
        }

        mealRepository
            .getMeals()
            .onSuccess { meals ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = null,
                        meals = meals
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.toUiText(),
                        meals = emptyList(),
                    )
                }
            }
    }
}
