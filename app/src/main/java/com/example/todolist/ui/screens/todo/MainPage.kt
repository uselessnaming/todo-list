package com.example.todolist.ui.screens.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolist.ui.theme.TodoListTheme

@Composable
fun MainPage(){

}

@Preview
@Composable
fun TestMainPage(){
    TodoListTheme {
        Box(
            modifier = Modifier.fillMaxSize().background(color = White)
        ){
            MainPage()
        }
    }
}