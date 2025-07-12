package com.example.kot.todo.domain.repository

import com.example.kot.todo.domain.model.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodos(): Flow<List<TodoItem>>
    suspend fun addTodo(title: String)
    suspend fun updateTodo(id: Long, isDone: Boolean)
    suspend fun deleteTodo(id: Long)
}
