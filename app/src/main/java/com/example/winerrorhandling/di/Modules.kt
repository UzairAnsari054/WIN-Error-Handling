package com.example.winerrorhandling.di

import com.example.winerrorhandling.core.data.HttpClientFactory
import com.example.winerrorhandling.meal_list.data.network.KtorRemoteMealDataSource
import com.example.winerrorhandling.meal_list.data.network.RemoteMealDataSource
import com.example.winerrorhandling.meal_list.data.repository.DefaultMealRepository
import com.example.winerrorhandling.meal_list.domain.MealRepository
import com.example.winerrorhandling.meal_list.presentation.MealListViewModel
import io.ktor.client.engine.HttpClientEngine
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModule = module {
    single<HttpClientEngine> { OkHttp.create() }
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteMealDataSource).bind<RemoteMealDataSource>()
    singleOf(::DefaultMealRepository).bind<MealRepository>()

    viewModelOf(::MealListViewModel)
}