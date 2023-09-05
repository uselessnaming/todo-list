package com.example.todolist.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolist.ui.theme.TodoListTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title : String,
    homeIcon : () -> Unit = {},
){
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            homeIcon
        }
    )
}

@Preview
@Composable
fun TestTopAppBar(){
    TodoListTheme {
        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            TopBar(title = "title")
        }
    }
}