package com.example.todo.model

enum class Priority(val label: String, val level: Int) {
    HIGH("High", 3),
    MEDIUM("Medium", 2),
    LOW("Low", 1)
}