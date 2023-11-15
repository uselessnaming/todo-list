package com.example.todolist.ui.screens.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.theme.TodoListTheme

@Composable
fun AddTodoPage(
    navController: NavController,
//    todoViewModel : TodoViewModel
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 10.dp, vertical = 15.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Todo 등록하기",
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight(600)
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Column(
                modifier = Modifier.weight(1f)
            ){

            }
            Column(
                modifier = Modifier.weight(5f)
            ){

            }
        }
    }
}

@Preview
@Composable
fun TestAddTodoPage(){
    TodoListTheme {
        AddTodoPage(
            navController = rememberNavController(),
//            todoViewModel = hiltViewModel()
        )
    }
}