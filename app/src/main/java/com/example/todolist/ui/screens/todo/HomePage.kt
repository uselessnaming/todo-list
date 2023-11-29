package com.example.todolist.ui.screens.todo

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todolist.Data.showToast
import com.example.todolist.Screens
import com.example.todolist.ui.components.MenuFAB
import com.example.todolist.ui.components.TopBar
import com.example.todolist.viewModel.TodoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar

@Composable
fun HomePage(
    navController: NavController,
    todoViewModel : TodoViewModel
){
    val TAG = "HomePage"

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    //화면 변수
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    //todos
    val todos = listOf("title","title2","title3")

    //뒤로가기 클릭 여부
    var backPressed by remember{mutableStateOf(false)}

    //drawer 상태
    val isMenuClicked = remember{mutableStateOf(false)}

    //Calendar 객체
    val calendar = Calendar.getInstance()

    //year
    var year by remember{mutableStateOf(calendar.get(Calendar.YEAR))}

    //back event
    BackHandler {
        //뒤로가기 두 번에 종료
        if (backPressed){
            (context as? Activity)?.finish()
        } else {
            backPressed = true
            showToast(context,"한 번 더 누르면 앱이 종료됩니다.")

            coroutineScope.launch(Dispatchers.Main){
                delay(2000)
                backPressed = false
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(horizontal = 20.dp)
        ){
            TopBar(
                title = "Todo-List",
                navIcon = null,
                actionIcon = Icons.Filled.Notifications,
                actionDes = "Menu",
                actionSize = 30.dp,
                onActionClick = {
                    /** Dropdown Menu 열기 */
                }
            )

            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                /** 날짜를 선택할 수 있는 Spinner */

                /** /날짜/ + /</ + />/ */
            }
        }
        //floating action button
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(end = 20.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ){
            MenuFAB(
                insertTodo = {
                    navController.navigate(Screens.AddTodoPage.name)
                }
            )
        }
    }


    /* App Drawer 사용 x / 
    AppDrawer(
        navController = navController,
        doLogout = {
            todoViewModel.logout()
            showToast(context, "로그아웃")
            navController.navigate(Screens.LoginPage.name){
                popUpTo(navController.graph.startDestinationId){
                    saveState = true
                }
                launchSingleTop = true
            }
        },
        drawerState = drawerState,
        closeDrawer = {
            coroutineScope.launch{
                drawerState.close()
            }
        },
        screenWidth = (configuration.screenWidthDp * 0.7).dp,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = White)
            ){
                //여기 내용을 밖으로 꺼내서 사용 혹시 필요하면 다시 안으로 넣어서 사용
            }
        }
    )*/
}