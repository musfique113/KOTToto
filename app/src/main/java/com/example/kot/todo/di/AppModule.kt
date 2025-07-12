package com.example.kot.todo.di

import com.example.kot.todo.data.DatabaseDriverFactory
import com.example.kot.todo.data.repository.TodoRepositoryImpl
import com.example.kot.todo.domain.repository.TodoRepository
import com.example.kot.todo.presentation.TodoViewModel
import com.example.kottodo.cache.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { DatabaseDriverFactory(androidContext()) }
    single { AppDatabase(get<DatabaseDriverFactory>().createDriver()) }
    single<TodoRepository> { TodoRepositoryImpl(get()) }
    viewModel { TodoViewModel(get()) }
}