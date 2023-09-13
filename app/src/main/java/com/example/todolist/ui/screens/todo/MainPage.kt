package com.example.todolist.ui.screens.todo

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolist.LoginPage
import com.example.todolist.Screens
import com.example.todolist.ui.theme.TodoListTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    navController : NavController
){
    val TAG = "MainPage"

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val items = listOf(
        Icons.Filled.Person,
        Icons.Filled.Settings
    )

    val coroutineScope = rememberCoroutineScope()
    var selectedMenu by remember{ mutableStateOf(items[0]) }

    ModalNavigationDrawer(
        modifier = Modifier.fillMaxSize()
            .background(color = White),
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet{
                Spacer(Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(color = Color.Transparent, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Profile Icons"
                    )
                }
                Spacer(Modifier.height(25.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "환영합니다.",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                )
                items.forEach{ item ->
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                imageVector = item,
                                contentDescription = "Menu Icons"
                            )
                        },
                        label = {
                            Text(
                                text = "sub menu"
                            )
                        },
                        selected = item == selectedMenu,
                        onClick = {
                            coroutineScope.launch(Dispatchers.Main){
                                drawerState.close()
                            }
                            selectedMenu = item
                        }
                    )
                }
            }
        }
    ) {
        HomePage(
            onActionClick = {
                coroutineScope.launch(Dispatchers.Main){
                    Log.d(TAG, "try to oepn")
                    drawerState.open()
                    Log.d(TAG,"drawer state : ${drawerState.currentValue}")
                }
            }
        )
    }
    LaunchedEffect(selectedMenu){
        when(selectedMenu){
            Icons.Filled.Person -> navController.navigate(Screens.MyPage.name)
            Icons.Filled.Settings -> navController.navigate(Screens.SettingPage.name)
        }
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