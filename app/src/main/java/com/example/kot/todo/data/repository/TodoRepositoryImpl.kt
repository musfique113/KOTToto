package com.example.kot.todo.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.kot.todo.domain.model.TodoItem
import com.example.kot.todo.domain.repository.TodoRepository
import com.example.kottodo.cache.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class TodoRepositoryImpl(db: AppDatabase) : TodoRepository {

    private val queries = db.appDatabaseQueries

    override fun getTodos(): Flow<List<TodoItem>> {
        return queries.selectAll().asFlow().mapToList(Dispatchers.IO).map { entities ->
            entities.map { entity ->
                TodoItem(
                    id = entity.id,
                    title = entity.title,
                    isDone = entity.isDone == 1L
                )
            }
        }
    }


    override suspend fun addTodo(title: String) {
        withContext(Dispatchers.IO){
            queries.insert(title = title, isDone = 0L)
        }
    }

    override suspend fun updateTodo(id: Long, isDone: Boolean) {
        withContext(Dispatchers.IO){
            queries.updateDone(if (isDone) 1L else 0L, id)
        }
    }

    override suspend fun deleteTodo(id: Long) {
        withContext(Dispatchers.IO){
            queries.deleteById(id)
        }
    }
}