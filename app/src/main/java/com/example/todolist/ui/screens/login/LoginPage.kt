package com.example.todolist

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todolist.Data.SignInReqDto
import com.example.todolist.Data.showToast
import com.example.todolist.Module.TodoViewModel
import com.example.todolist.ui.theme.MainColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(
    navController : NavController,
    todoViewModel : TodoViewModel = hiltViewModel()
){
    val TAG = "LoginPage"

    var id by remember{mutableStateOf("")}
    var passwd by remember{mutableStateOf("")}

    val context = LocalContext.current

    //login 상태
    val isLogin = todoViewModel.isLogin.collectAsState()
    Log.d(TAG, "isLogin : ${isLogin}")

    //coroutineScope
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(top = 10.dp, start = 20.dp, end = 20.dp)
    ){
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "로그인",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.weight(1f))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = id,
            onValueChange = {
                id = it
            },
            placeholder = {
                Text(
                    text = "아이디를 입력해주세요",
                    fontSize = 20.sp,
                    color = LightGray
                )
            },
            textStyle = TextStyle(
                fontSize = 20.sp,
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Black
            ),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            )
        )

        Spacer(Modifier.height(30.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = passwd,
            onValueChange = {
                passwd = it
            },
            placeholder = {
                Text(
                    text = "비밀번호를 입력해주세요",
                    fontSize = 20.sp,
                    color = LightGray
                )
            },
            textStyle = TextStyle(
                fontSize = 20.sp,
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Black
            ),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
            ),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(Modifier.height(30.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                //id와 passwd가 있을 경우
                if(id.isNotEmpty() && passwd.isNotEmpty()){
                    val loginReqDto = SignInReqDto(
                        password = passwd,
                        id = id
                    )
                    coroutineScope.launch{
                        val message = todoViewModel.signIn(loginReqDto)

                        withContext(Dispatchers.Main){
                            if (isLogin.value){
                                navController.navigate(Screens.HomePage.name)
                            } else {
                                showToast(context, message)
                            }
                        }
                    }
                }
                //id와 passwd가 없을 경우
                else {
                    if (id.isEmpty()){
                        showToast(context, "id를 입력해주세요")
                    } else if(passwd.isEmpty()){
                        showToast(context, "password를 입력해주세요")
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MainColor
            ),
        ) {
            Text(
                text = "로그인",
                fontSize = 20.sp,
                color = Black
            )
        }

        Spacer(Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        navController.navigate(Screens.AddUserPage.name)
                    },
                text = "회원가입",
                color = LightGray,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable {

                    },
                text = "아이디 찾기",
                color = LightGray,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable {

                    },
                text = "비밀번호 찾기",
                color = LightGray,
                textAlign = TextAlign.Center
            )
        }

        Spacer(Modifier.weight(1f))
    }
}