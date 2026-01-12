package com.example.winerrorhandling.meal_list.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun MealListScreenRoot(
    viewModel: MealListViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    MealListScreen(
        state = state,
    )
}

@Composable
private fun MealListScreen(
    state: MealListState
) {
    if (state.errorMessage != null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = state.errorMessage.asString(),
                fontSize = 32.sp
            )
        }
    } else {
        Scaffold {
            LazyColumn(
                modifier = Modifier.padding(it)
            ) {
                items(
                    items = state.meals
                ) { meal ->
                    MealItem(
                        mealName = meal.name,
                        mealCategory = meal.category
                    )
                }
            }
        }
    }
}

@Composable
fun MealItem(
    modifier: Modifier = Modifier,
    mealName: String,
    mealCategory: String,
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = mealName,
                fontSize = 32.sp
            )

            Text(
                text = mealCategory,
                fontSize = 24.sp
            )
        }
    }
}