package com.example.todolist.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
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
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.todolist.ui.theme.TodoListTheme
import java.util.Calendar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TimePickerDialog(
    modifier : Modifier,
    onDismissRequest : () -> Unit,
    onUpdateDate : (Triple<Int, Int, String>) -> Unit,
    selectedHour : Int,
    selectedMinute : Int,
    am_pm : String,
    am_pms : List<String> = listOf("오전","오후"),
    hours : List<Int>,
    minutes : List<Int>,
    onHourChanged : (Int) -> Unit,
    onMinuteChanged : (Int) -> Unit,
    onAmPmChanged : () -> Unit,
){
    val amPmListState = rememberLazyListState()
    val hourListState = rememberLazyListState()
    val minuteListState = rememberLazyListState()

    Dialog(
        onDismissRequest = onDismissRequest
    ){
        Column(
            modifier = modifier
                .padding(horizontal = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){

                //오전 오후
                Column(
                    modifier = Modifier.weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ){
                    Spacer(Modifier.height(15.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth()
                            .height(25.dp)
                            .clickable {
                                onAmPmChanged()
                            },
                        text = am_pm,
                        textAlign = TextAlign.Center
                    )
                }

                //시간 선택
                LazyColumn(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    state = hourListState
                ){
                    items(hours){
                        if (it != -1){
                            val isSelected = selectedHour == it
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(25.dp)
                                    .border(
                                        width = 0.5.dp,
                                        color = if (isSelected) LightGray else Unspecified,
                                        shape = RoundedCornerShape(15.dp)
                                    )
                                    .background(
                                        color = if (isSelected) LightGray else Unspecified,
                                        shape = RoundedCornerShape(15.dp)
                                    )
                                    .clickable {
                                        onHourChanged(it)
                                    },
                                text = "${it}",
                                textAlign = TextAlign.Center,
                            )
                        }
                        else {
                            Spacer(Modifier.height(25.dp))
                        }
                    }
                }

                //분 선택
                LazyColumn(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    state = minuteListState
                ){
                    items(minutes){
                        if (it != -1){
                            val isSelected = selectedMinute == it
                            Text(
                                modifier = Modifier
                                    .height(25.dp)
                                    .fillMaxWidth()
                                    .border(
                                        width = 0.5.dp,
                                        color = if (isSelected) LightGray else Unspecified,
                                        shape = RoundedCornerShape(15.dp)
                                    )
                                    .background(
                                        color = if (isSelected) LightGray else Unspecified,
                                        shape = RoundedCornerShape(15.dp)
                                    )
                                    .clickable {
                                        onMinuteChanged(it)
                                    },
                                text = "${it}",
                                textAlign = TextAlign.Center
                            )
                        }
                        else {
                            Spacer(Modifier.height(25.dp))
                        }
                    }
                }
            }
            Spacer(Modifier.weight(1f))

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
            )
            Row(
                modifier = Modifier.height(40.dp)
            ){
                Text(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .padding(5.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            //취소 이벤트
                            onDismissRequest()
                        },
                    text = "취소",
                    textAlign = TextAlign.Center
                )

                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                )

                Text(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .padding(5.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            //확인 이벤트
                            onUpdateDate(Triple(selectedHour,selectedMinute,am_pm))
                            onDismissRequest()
                        },
                    text = "확인",
                    textAlign = TextAlign.Center
                )
            }
        }
        LaunchedEffect(selectedHour){
            val itemHeight = 30.dp.value
            val total = hourListState.layoutInfo.viewportSize.height - itemHeight
            hourListState.scrollToItem(
                index = hours.indexOf(selectedHour),
                scrollOffset = -(total/2).toInt()
            )
        }
        LaunchedEffect(selectedMinute){
            val itemHeight = 30.dp.value
            val total = minuteListState.layoutInfo.viewportSize.height - itemHeight
            minuteListState.scrollToItem(
                index = minutes.indexOf(selectedMinute),
                scrollOffset = -(total/2).toInt()
            )
        }
        LaunchedEffect(am_pm){
            amPmListState.scrollToItem(am_pms.indexOf(am_pm))
        }
    }
}

@Composable
@Preview
fun TestTimePickerDialog(){
    TodoListTheme {

        val calendar = Calendar.getInstance()

        var hour by remember{mutableStateOf(calendar.get(Calendar.HOUR))}
        var minute by remember{mutableStateOf(calendar.get(Calendar.MINUTE))}
        var am_pm by remember{mutableStateOf(if (hour > 12) "오후" else "오전")}

        //hour가 12보다 클 떄
        val new_hour = if (hour > 12) hour - 12 else hour
        //minute가 5단위로 끊어지지 않을때
        val new_minute = if (minute % 5 != 0){minute - (minute % 5)} else {minute}

        val hours = mutableListOf(-1,-1,-1,-1,-1,-1)
        hours.addAll((0..12).toList())
        hours.addAll(listOf(-1,-1,-1,-1,-1,-1))
        val minutes = mutableListOf(-1,-1,-1,-1,-1,-1)
        minutes.addAll((0..55 step(5)).toList())
        minutes.addAll(listOf(-1,-1,-1,-1,-1,-1))
        var selectedHour by remember{ mutableStateOf(new_hour) }
        var selectedMinute by remember{ mutableStateOf(new_minute) }

        var showTimePicker by remember{ mutableStateOf(true) }

        val configuration = LocalConfiguration.current
        val width = configuration.screenWidthDp.dp
        val height = configuration.screenHeightDp.dp

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = White),
            contentAlignment = Alignment.Center
        ){
            if (showTimePicker){
                TimePickerDialog(
                    modifier = Modifier
                        .width((width * 4) / 5)
                        .height(height / 2)
                        .background(color = White, shape = RoundedCornerShape(25.dp)),
                    onDismissRequest = {
                        showTimePicker = false
                    },
                    selectedHour = selectedHour,
                    selectedMinute = selectedMinute,
                    am_pm = am_pm,
                    onUpdateDate = {
                        hour = it.first
                        minute = it.second
                        am_pm = it.third
                    },
                    hours = hours,
                    minutes = minutes,
                    onHourChanged = {
                        selectedHour = it
                    },
                    onMinuteChanged = {
                        selectedMinute = it
                    },
                    onAmPmChanged = {
                        am_pm = if (am_pm == "오전") "오후" else "오전"
                    }
                )
            }
            Text(
                modifier = Modifier.fillMaxSize(),
                text = "Time : ${am_pm} ${selectedHour}시 ${selectedMinute}분",
                fontSize = 24.sp,
                color = Black
            )
        }
    }
}