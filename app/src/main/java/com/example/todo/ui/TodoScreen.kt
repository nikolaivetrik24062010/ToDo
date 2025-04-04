package com.example.todo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todo.model.Priority
import com.example.todo.model.TodoItem
import androidx.compose.foundation.lazy.items
import com.example.todo.viewmodel.TodoViewModel

@Composable
fun TodoScreen(viewModel: TodoViewModel) {
    var title by remember { mutableStateOf("") }
    var selectedPriority by remember { mutableStateOf(Priority.MEDIUM) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Task Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        PriorityDropdown(
            selected = selectedPriority,
            onPrioritySelected = { selectedPriority = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Button(onClick = {
                viewModel.addTask(title, selectedPriority)
                title = ""
            }) {
                Text("Add Task")
            }

            Spacer(Modifier.width(8.dp))

            Button(onClick = {
                viewModel.addNote(title, selectedPriority)
                title = ""
            }) {
                Text("Add Note")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(viewModel.todoList) { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        when (item) {
                            is TodoItem.Task -> Text("✅ ${item.title} (${item.priority.label})")
                            is TodoItem.Note -> Text("📝 ${item.title} (${item.priority.label})")
                        }
                    }

                    Button(
                        onClick = { viewModel.removeItem(item) },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                    ) {
                        Text("Remove")
                    }
                }
            }
        }
    }
}

@Composable
fun PriorityDropdown(
    selected: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }) {
            Text("Priority: ${selected.label}")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            Priority.entries.forEach { priority ->
                DropdownMenuItem(
                    text = { Text(priority.label) },
                    onClick = {
                        onPrioritySelected(priority)
                        expanded = false
                    }
                )
            }
        }
    }
}