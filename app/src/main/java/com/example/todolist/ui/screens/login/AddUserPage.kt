package com.example.todolist.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.theme.TodoListTheme

@Composable
fun AddUserPage(
    navController : NavController
){
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = White)
            .padding(start = 20.dp, end = 20.dp, top = 10.dp)
    ){
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "회원가입",
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )

        Spacer(Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
        ){
            Text(
                modifier = Modifier.weight(2f),
                text = "아이디",
                fontSize = 20.sp,
                fontWeight = FontWeight(600)
            )
        }

        Spacer(Modifier.weight(1f))
    }
}

@Preview
@Composable
fun TestAddUserPage(){
    TodoListTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            AddUserPage(navController = rememberNavController())
        }
    }
}