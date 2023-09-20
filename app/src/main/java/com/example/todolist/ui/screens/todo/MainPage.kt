package com.example.todolist.ui.screens.todo

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolist.Data.showToast
import com.example.todolist.LoginPage
import com.example.todolist.Module.TodoViewModel
import com.example.todolist.Screens
import com.example.todolist.ui.theme.TodoListTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    navController : NavController,
    todoViewModel : TodoViewModel = hiltViewModel()
){
    val TAG = "MainPage"
    val context = LocalContext.current

    //drawer 상태
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    //menu 아이템
    val items = listOf(
        Icons.Filled.Person,
        Icons.Filled.Settings
    )
    var selectedMenu by remember{ mutableStateOf(items[0]) }

    val coroutineScope = rememberCoroutineScope()

    //로그인 여부
    val isLogin = todoViewModel.isLogin.collectAsState()

    Log.d(TAG,"in MainPage")
    Log.d(TAG,"isLogin : ${isLogin.value}")
    if (isLogin.value){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = White)
        ){
            Text(
                text = "test",
                fontSize = 80.sp
            )
//            ModalNavigationDrawer(
//                drawerState = drawerState,
//                drawerContent = {
//                    ModalDrawerSheet{
//                        Spacer(Modifier.height(10.dp))
//                        Box(
//                            modifier = Modifier
//                                .size(40.dp)
//                                .background(color = Transparent, shape = CircleShape),
//                            contentAlignment = Alignment.Center
//                        ){
//                            Icon(
//                                modifier = Modifier.fillMaxSize(),
//                                imageVector = Icons.Filled.Person,
//                                contentDescription = "Profile Icons"
//                            )
//                        }
//                        Spacer(Modifier.height(25.dp))
//                        Text(
//                            modifier = Modifier.fillMaxWidth(),
//                            text = "환영합니다.",
//                            textAlign = TextAlign.Center,
//                            fontSize = 30.sp,
//                        )
//                        items.forEach{ item ->
//                            NavigationDrawerItem(
//                                icon = {
//                                    Icon(
//                                        modifier = Modifier.size(20.dp),
//                                        imageVector = item,
//                                        contentDescription = "Menu Icons"
//                                    )
//                                },
//                                label = {
//                                    Text(
//                                        text = "sub menu"
//                                    )
//                                },
//                                selected = item == selectedMenu,
//                                onClick = {
//                                    coroutineScope.launch(Dispatchers.Main){
//                                        drawerState.close()
//                                    }
//                                    selectedMenu = item
//                                }
//                            )
//                        }
//                    }
//                }
//            ) {
//                HomePage(
//                    onActionClick = {
//                        coroutineScope.launch(Dispatchers.Main){
//                            Log.d(TAG, "try to oepn")
//                            drawerState.open()
//                            Log.d(TAG,"drawer state : ${drawerState.currentValue}")
//                        }
//                    }
//                )
//            }
//
//            LaunchedEffect(selectedMenu){
//                when(selectedMenu){
//                    Icons.Filled.Person -> navController.navigate(Screens.MyPage.name)
//                    Icons.Filled.Settings -> navController.navigate(Screens.SettingPage.name)
//                }
//            }
        }
    } else {
        showToast(context, "정보 오류\n다시 로그인 해주세요")
        navController.navigate(Screens.LoginPage.name)
    }
}

@Preview
@Composable
fun TestMainPage(){
    TodoListTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = White)
        ){
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screens.LoginPage.name
            ){
                composable(Screens.LoginPage.name){
                    LoginPage(navController)
                }
                composable(Screens.MainPage.name){
                    MainPage(navController)
                }
                composable(Screens.SettingPage.name){
//                            SettingPage()
                }
                composable(Screens.MyPage.name){
//                            MyPage()
                }
            }
            MainPage(
                navController = navController
            )
        }
    }
}