package com.example.todo.model

sealed class TodoItem {
    abstract val title: String
    abstract val priority: Priority

    data class Task(
        override val title: String,
        override val priority: Priority,
        val isDone: Boolean = false
    ) : TodoItem()

    data class Note(
        override val title: String,
        override val priority: Priority
    ) : TodoItem()
}