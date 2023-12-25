package com.example.todolist.ui.screens.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolist.Data.DataClass.TodoGroupInTodo
import com.example.todolist.Screens
import com.example.todolist.ui.components.TodoGroupItem
import com.example.todolist.ui.components.TopBar
import com.example.todolist.ui.theme.TodoListTheme

@Composable
fun TodoGroupPage(
    navController: NavController,
//    todoViewModel : TodoViewModel = hiltViewModel()
){
    val TAG = "TodoGroupPage"

    val todoGroups = listOf<TodoGroupInTodo>()

    var showDialog by remember{mutableStateOf(false)}

    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(textDecoration = TextDecoration.Underline)
        ){
            append("Group을 추가하고 싶으시면 클릭해주세요")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ){
        TopBar(
            title = "Todo Group Page",
            navIcon = Icons.Filled.Home,
            onNavClick = {
                navController.navigate(Screens.HomePage.name)
            },
            actionIcon = Icons.Filled.List,
        )

        Spacer(Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
        ) {
            LazyColumn{
                items(todoGroups){
                    TodoGroupItem(
                        it
                    )
                }
            }
            Text(
                modifier = Modifier.fillMaxWidth()
                    .clickable{
//                        showDialog = true
                    },
                text = annotatedText,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = Color.DarkGray
            )
        }
    }
}

@Preview
@Composable
fun TestTodoGroupPage(){
    TodoListTheme {
        TodoGroupPage(navController = rememberNavController())
    }
}