package com.example.kot.todo.presentation

import com.example.kot.todo.domain.model.TodoItem

data class TodoState(
    val todos: List<TodoItem> = emptyList(),
    val newTodoTitle: String = ""
)