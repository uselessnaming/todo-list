package com.example.todolist.ui.screens.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.components.CustomDatePicker
import com.example.todolist.ui.components.CustomTimePicker
import com.example.todolist.ui.components.TopBar
import com.example.todolist.ui.theme.TodoListTheme
import com.example.todolist.viewModel.TodoViewModel

@Composable
fun AddTodoPage(
    navController: NavController,
    todoViewModel : TodoViewModel = hiltViewModel()
){
    var content by remember{mutableStateOf("")}
    var title by remember{mutableStateOf("")}
    var loc by remember{mutableStateOf("")}

    val today = todoViewModel.getToday()
    val today_date = today[0]

    var selectedStartHour by remember{mutableStateOf(todoViewModel.calendar.getToday()[1].toInt())}
    var selectedStartMinutes by remember{mutableStateOf(todoViewModel.calendar.getToday()[2].toInt())}
    var selectedStartAmPm by remember{mutableStateOf(if (selectedStartHour > 12) "오후" else "오전")}

    var selectedEndHour by remember{mutableStateOf(todoViewModel.calendar.getToday()[1].toInt())}
    var selectedEndMinutes by remember{mutableStateOf(todoViewModel.calendar.getToday()[2].toInt())}
    var selectedEndAmPm by remember{mutableStateOf(if (selectedEndHour > 12) "오후" else "오전")}

    val scrollState = rememberScrollState()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState)
    ){
        //TopBar
        TopBar(
            title = "Todo 등록하기",
            navIcon = Icons.Filled.KeyboardArrowLeft,
            actionIcon = null,
            onNavClick = {
                navController.navigateUp()
            },
            onActionClick = {}
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(horizontal = 10.dp, vertical = 15.dp)
        ) {

            Spacer(Modifier.height(15.dp))

            //todo title 입력
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ){
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Icon(
                        imageVector = Icons.Filled.Create,
                        contentDescription = "Title"
                    )
                }
                Column(
                    modifier = Modifier.weight(5f)
                ){
                    OutlinedTextField(
                        value = title,
                        onValueChange = {
                            title = it
                        },
                        placeholder = {
                            Text(
                                text = "제목을 입력해주세요",
                                color = Color.LightGray
                            )
                        },
                        maxLines = 1,
                        singleLine = true
                    )
                }
            }

            Spacer(Modifier.height(10.dp))

            //todo description 입력
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenWidth / 3)
            ){
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
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
                        modifier = Modifier.fillMaxHeight(),
                        value = content,
                        onValueChange = {
                            content = it
                        },
                        placeholder = {
                            Text(
                                text = "할 일을 입력해주세요",
                                color = Color.LightGray
                            )
                        }
                    )
                }
            }

            Spacer(Modifier.height(10.dp))

            //todo location 입력
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ){
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "Title"
                    )
                }
                Column(
                    modifier = Modifier.weight(5f)
                ){
                    OutlinedTextField(
                        value = loc,
                        onValueChange = {
                            loc = it
                        },
                        placeholder = {
                            Text(
                                text = "위치를 입력해주세요",
                                color = Color.LightGray
                            )
                        },
                        maxLines = 1,
                        singleLine = true
                    )
                }
            }

            Spacer(Modifier.height(10.dp))

            //todo startDate와 endDate 입력
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ){
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "Title"
                    )
                }
                Row(
                    modifier = Modifier.weight(5f)
                ){
                    //startDate 입력
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center
                    ){
                        //날짜 선택
                        CustomDatePicker(
                            modifier = Modifier,
                            date = today_date //viewmodel에서 오늘 날짜를 기본으로 설정
                        )

                        Spacer(Modifier.height(10.dp))

                        //시간 선택
                        CustomTimePicker(
                            modifier = Modifier.height(120.dp),
                            hour = selectedStartHour,
                            minute = selectedStartMinutes,
                            onUpdateDate = {
                                selectedStartHour = it.first
                                selectedStartMinutes = it.second
                                selectedStartAmPm = it.third
                            }
                        )
                    }

                    Spacer(Modifier.width(5.dp))

                    //endDate 입력
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center
                    ){
                        //날짜 선택
                        CustomDatePicker(
                            modifier = Modifier,
                            date = today_date
                        )

                        Spacer(Modifier.height(10.dp))

                        //시간 선택
                        CustomTimePicker(
                            modifier = Modifier.height(120.dp),
                            hour = selectedEndHour,
                            minute = selectedEndMinutes,
                            onUpdateDate = {
                                selectedEndHour = it.first
                                selectedEndMinutes = it.second
                                selectedEndAmPm = it.third
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TestAddTodoPage(){
    TodoListTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ){
            AddTodoPage(
                navController = rememberNavController(),
                todoViewModel = hiltViewModel()
            )
        }
    }
}