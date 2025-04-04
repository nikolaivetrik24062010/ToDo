package com.example.todo.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.todo.model.Priority
import com.example.todo.model.TodoItem

class TodoViewModel : ViewModel() {

    private val _todoList = mutableStateListOf<TodoItem>()
    val todoList: List<TodoItem> get() = _todoList.sortedByDescending { it.priority.level }

    fun addTask(title: String, priority: Priority) {
        TodoItem.Task(title, priority).also {
            _todoList.add(it)
        }
    }

    fun addNote(title: String, priority: Priority) {
        TodoItem.Note(title, priority).apply {
            _todoList.add(this)
        }
    }

    fun removeItem(item: TodoItem) {
        _todoList.remove(item)
    }
}