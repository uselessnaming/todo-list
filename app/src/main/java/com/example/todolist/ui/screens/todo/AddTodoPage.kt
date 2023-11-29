package com.example.todolist.ui.screens.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.theme.TodoListTheme
import com.example.todolist.viewModel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodoPage(
    navController: NavController,
    todoViewModel : TodoViewModel
){
    var content by remember{mutableStateOf("")}

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

        Spacer(Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
        ){
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "Content"
                )
            }
            Column(
                modifier = Modifier.weight(5f)
            ){
                OutlinedTextField(
                    value = content,
                    onValueChange = {
                        content = it
                    },

                )
            }
        }
    }
}

@Preview
@Composable
fun TestAddTodoPage(){
    TodoListTheme {
        Column(
            modifier = Modifier.fillMaxSize().background(color = Color.White)
        ){

        }
        AddTodoPage(
            navController = rememberNavController(),
            todoViewModel = hiltViewModel()
        )
    }
}