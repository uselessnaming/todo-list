package com.example.todolist.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todolist.Data.Todo
import com.example.todolist.Screens

@Composable
fun TodoGroupItem(
    todos : List<Todo>,
    navController : NavController,
){
    Column(
        modifier = Modifier.padding(start = 5.dp)
    ){
        todos.forEach{ todo ->
            TodoItem(
                todo = todo,
                onClick = {
                    navController.navigate("${Screens.DescriptionPage.name}/${todo.todoNum}")
                }
            )
        }
    }
}