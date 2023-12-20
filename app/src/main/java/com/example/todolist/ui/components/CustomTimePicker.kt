package com.example.todolist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun CustomTimePicker(
    modifier : Modifier = Modifier,
    hour : Int = 0,
    minute : Int = 0,
    onUpdateDate : (Triple<Int, Int, String>) -> Unit,
){

    //hour가 12보다 클 떄
    val new_hour = if (hour > 12) hour - 12 else hour
    //minute가 5단위로 끊어지지 않을때
    val new_minute = if (minute % 5 != 0){minute - (minute % 5)} else {minute}

    var am_pm by remember{mutableStateOf("오전")}
    val hours = (0..12).toList()
    val minutes = (0..55 step(5)).toList()
    var selectedHour by remember{mutableStateOf(new_hour)}
    var selectedMinute by remember{mutableStateOf(new_minute)}

    val hourFormatter = DateTimeFormatter.ofPattern("HH")
    val minuteFormatter = DateTimeFormatter.ofPattern("MM")
    val amPmFormatter = DateTimeFormatter.ofPattern("a")

    var showTimePicker by remember{mutableStateOf(false)}

    if (showTimePicker){
        TimePickerDialog(
            onDismissRequest = {
                showTimePicker = false
            },
            selectedHour = selectedHour,
            selectedMinute = selectedMinute,
            am_pm = am_pm,
            onUpdateDate = onUpdateDate,
            hours = hours,
            minutes = minutes,
            onHourChanged = {
                selectedHour = it
            },
            onMinuteChanged = {
                selectedMinute = it
            },
            onAmPmChanged = {
                am_pm = it
            },
            onFetchTime = {
                val current = LocalDateTime.now()
                selectedHour = current.format(hourFormatter).toInt()
                selectedMinute = current.format(minuteFormatter).toInt()
                am_pm = current.format(amPmFormatter)
            }
        )
    }

    /** SPinner Component를 생성해 이용 */
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){

//        Spinner 말고 Dialog를 통해서 오전/오후 시간 분 정할 수 있도록 하는 게 좋을 것 같음



    }
}

@Composable
@Preview
fun TestTimePicker(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ){
        CustomTimePicker(
            modifier = Modifier,
            hour = 5,
            minute = 10,
            onUpdateDate = {},
        )
    }
}