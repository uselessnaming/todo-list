package com.example.todolist.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.theme.TodoListTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUserPage(
    navController : NavController
){
    var newId by remember{mutableStateOf("")}
    var newPasswd by remember{mutableStateOf("")}
    var newName by remember{mutableStateOf("")}

    Column(
        modifier = Modifier
            .fillMaxSize()
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
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
        ){
            Text(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight(),
                text = "아이디",
                fontSize = 20.sp,
                fontWeight = FontWeight(600)
            )

            TextField(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                value = newId,
                onValueChange = {
                    newId = it
                },
                placeholder = {
                    Text(
                        text = "아이디를 입력해주세요",
                        fontSize = 20.sp,
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = White,
                    focusedIndicatorColor = Black,
                    unfocusedIndicatorColor = Black,
                    placeholderColor = LightGray
                )
            )
        }

        Spacer(Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
        ){
            Text(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight(),
                text = "비밀번호",
                fontSize = 20.sp,
                fontWeight = FontWeight(600)
            )

            TextField(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                value = newPasswd,
                onValueChange = {
                    newPasswd = it
                },
                placeholder = {
                    Text(
                        text = "비밀번호를 입력해주세요",
                        fontSize = 20.sp,
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = White,
                    focusedIndicatorColor = Black,
                    unfocusedIndicatorColor = Black,
                    placeholderColor = LightGray
                )
            )
        }

        Spacer(Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
        ){
            Text(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight(),
                text = "이름",
                fontSize = 20.sp,
                fontWeight = FontWeight(600)
            )

            TextField(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                value = newName,
                onValueChange = {
                    newName = it
                },
                placeholder = {
                    Text(
                        text = "이름을 입력해주세요",
                        fontSize = 20.sp,
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = White,
                    focusedIndicatorColor = Black,
                    unfocusedIndicatorColor = Black,
                    placeholderColor = LightGray
                )
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