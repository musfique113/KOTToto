package com.example.kot.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.kot.todo.ui.TodoScreen
import com.example.kot.todo.ui.theme.KOTTodoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KOTTodoTheme {
                TodoScreen()
            }
        }
    }
}