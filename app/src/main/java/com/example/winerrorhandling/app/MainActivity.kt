package com.example.winerrorhandling.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.winerrorhandling.app.theme.WINErrorHandlingTheme
import com.example.winerrorhandling.meal_list.presentation.MealListScreenRoot
import com.example.winerrorhandling.meal_list.presentation.MealListViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WINErrorHandlingTheme {
                val viewModel = koinViewModel<MealListViewModel>()
                MealListScreenRoot(viewModel = viewModel)
            }
        }
    }
}