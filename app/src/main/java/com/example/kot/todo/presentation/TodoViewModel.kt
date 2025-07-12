package com.example.kot.todo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kot.todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TodoViewModel(private val todoRepository: TodoRepository) : ViewModel() {

    private val _state = MutableStateFlow(TodoState())
    val state = _state.asStateFlow()

    init {
        loadTodos()
    }

    private fun loadTodos() {
        todoRepository.getTodos().onEach { todos ->
            _state.value = TodoState(todos = todos)
        }.launchIn(viewModelScope)
    }

    fun onNewTodoTitleChange(newTitle: String) {
        _state.value = _state.value.copy(newTodoTitle = newTitle)
    }

    fun addTodo() {
        viewModelScope.launch {
            todoRepository.addTodo(state.value.newTodoTitle)
            _state.value = _state.value.copy(newTodoTitle = "")
        }
    }

    fun updateTodo(id: Long, isDone: Boolean) {
        viewModelScope.launch {
            todoRepository.updateTodo(id, isDone)
        }
    }

    fun deleteTodo(id: Long) {
        viewModelScope.launch {
            todoRepository.deleteTodo(id)
        }
    }
}