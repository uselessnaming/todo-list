package com.example.todolist.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolist.Data.SignUpReqDto
import com.example.todolist.Module.TodoViewModel
import com.example.todolist.ui.theme.MainColor
import com.example.todolist.ui.theme.SubColor1
import com.example.todolist.ui.theme.TodoListTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUserPage(
    navController : NavController,
    todoViewModel : TodoViewModel = hiltViewModel()
){
    val coroutine = rememberCoroutineScope()

    var newId by remember{mutableStateOf("")}
    var newPasswd by remember{mutableStateOf("")}
    var newEmail by remember{mutableStateOf("")}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
            .padding(start = 20.dp, end = 20.dp, top = 10.dp),
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
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier
                    .weight(1f),
                text = "아이디",
                fontSize = 18.sp,
                fontWeight = FontWeight(600)
            )

            TextField(
                modifier = Modifier
                    .weight(2.5f)
                    .fillMaxHeight(),
                value = newId,
                onValueChange = {
                    newId = it
                },
                placeholder = {
                    Text(
                        text = "아이디를 입력해주세요",
                        fontSize = 18.sp,
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Black,
                    containerColor = White,
                    focusedIndicatorColor = Black,
                    unfocusedIndicatorColor = Black,
                    placeholderColor = LightGray
                ),
            )
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SubColor1,
                    contentColor = Red
                ),
                contentPadding = PaddingValues(horizontal = 0.dp),
                onClick = {

                }
            ) {
                Text(
                    text = "중복확인",
                    fontSize = 10.sp,
                )
            }
        }

        Spacer(Modifier.height(45.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier
                    .weight(1f),
                text = "비밀번호",
                fontSize = 18.sp,
                fontWeight = FontWeight(600)
            )

            TextField(
                modifier = Modifier
                    .weight(3.5f)
                    .fillMaxHeight(),
                value = newPasswd,
                onValueChange = {
                    newPasswd = it
                },
                placeholder = {
                    Text(
                        text = "비밀번호를 입력해주세요",
                        fontSize = 18.sp,
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

        Spacer(Modifier.height(45.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier
                    .weight(1f),
                text = "이메일",
                fontSize = 18.sp,
                fontWeight = FontWeight(600)
            )

            TextField(
                modifier = Modifier
                    .weight(3.5f)
                    .fillMaxHeight(),
                value = newEmail,
                onValueChange = {
                    newEmail = it
                },
                placeholder = {
                    Text(
                        text = "이메일을 입력해주세요",
                        fontSize = 18.sp,
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

        Spacer(Modifier.height(60.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MainColor
                ),
                onClick = {
                    navController.navigateUp()
                }
            ) {
                Text(
                    text = "취소",
                    fontSize = 20.sp,
                    color = Black,
                )
            }

            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MainColor
                ),
                onClick = {
                    val newUser = SignUpReqDto(
                        password = newPasswd,
                        role = arrayListOf("ROLE_USER"),
                        email = newEmail,
                        userName = newId
                    )
                    //newUser를 서버에 추가
                    coroutine.launch(Dispatchers.IO){
                        todoViewModel.addUser(newUser)
                    }
                }
            ) {
                Text(
                    text = "추가",
                    fontSize = 20.sp,
                    color = Black,
                )
            }
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