package com.example.todolist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.ui.theme.TodoListTheme

@Composable
fun AppDrawer(){

    //drawer 상태
    var drawerState by remember{mutableStateOf(false)}

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.End
    ){
        if(drawerState){
            Column(
                modifier = Modifier
                    .width(((screenWidth / 3) * 2).dp)
                    .fillMaxHeight()
                    .background(color = White),
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        modifier = Modifier.size(60.dp),
                        imageVector = Icons.Filled.Person,
                        contentDescription = "마이페이지"
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "마이페이지",
                        fontSize = 26.sp,
                        color = Black,
                    )
                }
                Spacer(Modifier.height(5.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        modifier = Modifier.size(60.dp),
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "설정"
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "설정",
                        fontSize = 26.sp,
                        color = Black,
                    )
                }

                Spacer(Modifier.weight(1f))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    horizontalArrangement = Arrangement.Start
                ){
                    IconButton(
                        modifier = Modifier.size(60.dp),
                        onClick = {
                            drawerState = !drawerState
                        }
                    ) {
                        Icon(
                            modifier = Modifier.fillMaxSize(),
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "메뉴 나가기"
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = if(!drawerState) White else LightGray)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                horizontalArrangement = Arrangement.End
            ){
                Spacer(Modifier.width(20.dp))

                IconButton(
                    modifier = Modifier.size(80.dp),
                    onClick = {
                        drawerState = !drawerState
                    }
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu Button"
                    )
                }
            }

            Text(
                text = "테스트 배경",
                fontSize = 50.sp
            )
        }
    }


}

@Preview
@Composable
fun TestAppDrawer(){
    TodoListTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = White)
        ){
            AppDrawer()
        }
    }
}