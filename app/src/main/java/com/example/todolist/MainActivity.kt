package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.screens.login.AddUserPage
import com.example.todolist.ui.screens.login.FindIdPage
import com.example.todolist.ui.screens.login.FindPasswdPage
import com.example.todolist.ui.screens.todo.MainPage
import com.example.todolist.ui.theme.TodoListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screens.LoginPage.name
                    ){
                        composable(Screens.AddUserPage.name){
                            AddUserPage(navController)
                        }
                        composable(Screens.FindIdPage.name){
                            FindIdPage(navController)
                        }
                        composable(Screens.FindPasswdPage.name){
                            FindPasswdPage(navController)
                        }
                        composable(Screens.LoginPage.name){
                            LoginPage(navController)
                        }
                        composable(Screens.MainPage.name){
                            val parentEntry = remember(it){
                                navController.getBackStackEntry(Screens.LoginPage.name)
                            }
                            MainPage(
                                navController = navController,
                                todoViewModel = hiltViewModel(parentEntry)
                            )
                        }
                        composable(Screens.SettingPage.name){
//                            SettingPage()
                        }
                        composable(Screens.MyPage.name){
//                            MyPage()
                        }
                    }
                }
            }
        }
    }
}