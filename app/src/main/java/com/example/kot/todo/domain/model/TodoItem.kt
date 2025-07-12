package com.example.kot.todo.domain.model

data class TodoItem(
    val id: Long,
    val title: String,
    val isDone: Boolean
)