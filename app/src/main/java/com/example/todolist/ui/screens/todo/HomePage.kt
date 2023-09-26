package com.example.todolist.ui.screens.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.ui.components.MenuFAB
import com.example.todolist.ui.components.Spinner
import com.example.todolist.ui.components.TodoItem
import com.example.todolist.ui.components.TopBar
import java.time.LocalDate

@Composable
fun HomePage(
    openDrawer : () -> Unit,
    closeDrawer : () -> Unit
){
    val months = listOf(0,0,0,0,1,2,3,4,5,6,7,8,9,10,11,12,0,0,0,0)
    val years = (2000..2050).toList()

    //선택된 month
    var selectedMonth by remember{mutableStateOf(LocalDate.now().monthValue+1)}

    //선택된 year
    var selectedYear by remember{mutableStateOf(LocalDate.now().year)}

    //scroll state
    val monthScrollState = rememberLazyListState(months.indexOf(selectedMonth))

    //화면 변수
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp - 85.dp
    val screenWidthPx = with(LocalDensity.current){screenWidth.toPx()}

    //todos
    val todos = listOf("title","title2","title3")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
    ){

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
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

                //년도 선택
                Row(
                    modifier = Modifier.fillMaxWidth()
                ){
                    Spinner(
                        modifier = Modifier.width(100.dp),
                        value = selectedYear,
                        onValueChanged = {
                            selectedYear = it
                        },
                        items = years
                    )
                }

                Spacer(Modifier.height(5.dp))

                //월 선택
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    LazyRow(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        state = monthScrollState
                    ){
                        items(months){
                            val isSelected = selectedMonth == it
                            Column(
                                modifier = Modifier.width(40.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(
                                            color = if (isSelected) LightGray else Transparent,
                                            shape = CircleShape
                                        )
                                        .clickable {
                                            if (it != 0) {
                                                selectedMonth = it
                                            }
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

                                if (it != 0){
                                    //월별 투두 개수
                                    Text(
                                        modifier = Modifier
                                            .width(40.dp)
                                            .height(20.dp),
                                        text = "total",
                                        fontSize = 14.sp,
                                        textAlign = TextAlign.Center,
                                        color = if(isSelected) Black else LightGray
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(Modifier.height(10.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ){
                    items(todos){
                        //item별 click 상태
                        var isClicked by remember{mutableStateOf(false)}

                        TodoItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    isClicked = !isClicked
                                }
                        )

                        //눌렀을 떄 세부 설명
                        if (isClicked){
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "세부 설명",
                                    fontSize = 14.sp,
                                )
                            }
                        }
                    }
                }

                //selectedMonth가 변결될 경우 Event
                LaunchedEffect(selectedMonth){
                    monthScrollState.animateScrollToItem(months.indexOf(selectedMonth),(-screenWidthPx/2).toInt())
                }
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
            MenuFAB()
        }
    }
}