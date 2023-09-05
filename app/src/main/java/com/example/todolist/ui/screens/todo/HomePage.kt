package com.example.todolist.ui.screens.todo

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.theme.TodoListTheme

@Composable
fun HomePage(
    navController: NavController
){
    Column(

    ){

    }
}

@Preview
@Composable
fun TestHomePage(){
    TodoListTheme {
        HomePage(navController = rememberNavController())
    }
}