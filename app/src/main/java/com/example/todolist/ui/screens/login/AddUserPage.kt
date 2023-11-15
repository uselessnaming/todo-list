package com.example.todolist.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalContext
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
import com.example.todolist.Data.showToast
import com.example.todolist.Screens
import com.example.todolist.ui.theme.MainColor
import com.example.todolist.ui.theme.SubColor1
import com.example.todolist.ui.theme.TodoListTheme
import com.example.todolist.viewModel.TodoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUserPage(
    navController : NavController,
    todoViewModel : TodoViewModel = hiltViewModel()
){
    val coroutine = rememberCoroutineScope()
    val context = LocalContext.current

    //추가할 id
    var newId by remember{mutableStateOf("")}
    //추가할 passwd
    var newPasswd by remember{mutableStateOf("")}
    //추가할 email
    var newEmail by remember{mutableStateOf("")}

    //중복 여부
    var isDuplicated by remember{mutableStateOf(true)}

    //변수 초기화
    val resetData = {
        newId = ""
        newPasswd = ""
        newEmail = ""
    }

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
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier
                    .weight(1f),
                text = "아이디",
                fontSize = 18.sp,
                fontWeight = FontWeight(600)
            )

            Spacer(Modifier.width(10.dp))

            BasicTextField(
                modifier = Modifier
                    .weight(2.5f),
                value = newId,
                onValueChange = {
                    newId = it
                },
                decorationBox = {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ){
                        if (newId.isEmpty()){
                            Text(
                                text = "아이디를 입력해주세요",
                                fontSize = 18.sp,
                                color = LightGray,
                            )
                        }
                        it()
                    }
                },
                textStyle = TextStyle(
                    color = Black,
                    background = White,
                ),
                singleLine = true,
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
                enabled = isDuplicated,
                onClick = {
                    /** 정아가 틀을 만들어야 가능 */
                    //중복 데이터가 없으면?
                    if(true){
                        isDuplicated = false
                        showToast(context,"사용 가능한 아이디 입니다.")
                    }
                    //중복 데이터가 있다면
                    else {
                        isDuplicated = true
                        showToast(context,"이미 있는 아이디 입니다.")
                    }
                }
            ) {
                Text(
                    text = "중복확인",
                    fontSize = 10.sp,
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier
                    .weight(1f),
                text = "비밀번호",
                fontSize = 18.sp,
                fontWeight = FontWeight(600)
            )

            Spacer(Modifier.width(10.dp))

            BasicTextField(
                modifier = Modifier
                    .weight(3.5f),
                value = newPasswd,
                onValueChange = {
                    newPasswd = it
                },
                decorationBox = {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ){
                        if (newPasswd.isEmpty()){
                            Text(
                                text = "비밀번호를 입력해주세요",
                                fontSize = 18.sp,
                                color = LightGray,
                            )
                        }
                        it()
                    }
                },
                textStyle = TextStyle(
                    color = Black,
                    background = White,
                ),
                singleLine = true,
            )
        }

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier
                    .weight(1f),
                text = "이메일",
                fontSize = 18.sp,
                fontWeight = FontWeight(600)
            )

            Spacer(Modifier.width(10.dp))

            BasicTextField(
                modifier = Modifier
                    .weight(3.5f),
                value = newEmail,
                onValueChange = {
                    newEmail = it
                },
                decorationBox = {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ){
                        if (newEmail.isEmpty()){
                            Text(
                                text = "이메일을 입력해주세요",
                                fontSize = 18.sp,
                                color = LightGray,
                            )
                        }
                        it()
                    }
                },
                textStyle = TextStyle(
                    color = Black,
                    background = White,
                ),
                singleLine = true,
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
                    resetData()
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
                        val resultMsg = todoViewModel.signUp(newUser)
                        withContext(Dispatchers.Main){
                            //성공할 경우
                            if (resultMsg == "SUCCESS"){
                                showToast(context, "회원 가입 성공")
                                resetData()
                                navController.navigate(Screens.LoginPage.name)
                            }
                            //중복일 경우
                            else if (resultMsg == "Duplicate"){
                                showToast(context, "동일한 아이디가 있습니다.")
                            }
                            //그 외 실패
                            else {
                                showToast(context, resultMsg)
                            }
                        }
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