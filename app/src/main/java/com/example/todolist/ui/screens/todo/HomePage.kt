package com.example.todolist.ui.screens.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.components.TopBar
import com.example.todolist.ui.theme.TodoListTheme
import java.time.LocalDate

@Composable
fun HomePage(
    navController: NavController
){
    val months = listOf(0,0,0,0,1,2,3,4,5,6,7,8,9,10,11,12,0,0,0,0)

    //선택된 month
    var selectedMonth by remember{mutableStateOf(LocalDate.now().monthValue+1)}

    //scroll state
    var monthScrollState = rememberLazyListState(months.indexOf(selectedMonth))

    //화면 변수
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp - 65.dp
    val screenWidthPx = with(LocalDensity.current){screenWidth.toPx()}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
            .padding(horizontal = 20.dp)
    ){
        TopBar(
            title = "홈페이지",
            navIcon = Icons.Filled.Home,
            navDes = "Home Icon",
            navSize = 30.dp,
            onNavClick = {

            },
            actionIcon = Icons.Filled.Menu,
            actionDes = "Menu",
            actionSize = 30.dp,
            onActionClick = {

            }
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            state = monthScrollState
        ){
            items(months){
                val isSelected = selectedMonth == it
                Box(
                    modifier = Modifier.size(40.dp)
                        .background(
                            color = if (isSelected) LightGray else Transparent,
                            shape = CircleShape
                        )
                        .clickable {
                            selectedMonth = it
                        },
                    contentAlignment = Alignment.Center
                ){
                    if (it == 0){
                        Spacer(Modifier.fillMaxSize())
                    } else {
                        Text(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 5.dp)
                                .focusable(isSelected),
                            text = it.toString(),
                            fontSize = 20.sp,
                            color = if(isSelected) Black else LightGray,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                LaunchedEffect(selectedMonth){
                    monthScrollState.animateScrollToItem(months.indexOf(selectedMonth),(-screenWidthPx/2).toInt())
                }
            }
        }
    }
}

@Preview
@Composable
fun TestHomePage(){
    TodoListTheme {
        HomePage(navController = rememberNavController())
    }
}