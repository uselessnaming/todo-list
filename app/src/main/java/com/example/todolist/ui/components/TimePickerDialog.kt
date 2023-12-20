package com.example.todolist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.todolist.ui.theme.TodoListTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

@Composable
fun TimePickerDialog(
    onDismissRequest : () -> Unit,
    onUpdateDate : (Triple<Int, Int, String>) -> Unit,
    selectedHour : Int,
    selectedMinute : Int,
    am_pm : String,
    hours : List<Int>,
    minutes : List<Int>,
    onHourChanged : (Int) -> Unit,
    onMinuteChanged : (Int) -> Unit,
    onAmPmChanged : (String) -> Unit,
    onFetchTime : () -> Unit
){
    Dialog(
        onDismissRequest = onDismissRequest
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier.clickable{
                    onAmPmChanged(if (am_pm == "오전") "오후" else "오전")
                    onUpdateDate(Triple(selectedHour, selectedMinute, am_pm))
                },
                text = am_pm,
            )

            Spinner(
                modifier = Modifier,
                value = selectedHour,
                onValueChanged = {
                    onHourChanged(it)
                    onUpdateDate(Triple(selectedHour, selectedMinute, am_pm))
                },
                items = hours,
                onUseFetch = true,
                onFetchTime = onFetchTime
            )
            Text(
                fontSize = 30.sp,
                text = ":",
            )
            Spinner(
                modifier = Modifier,
                value = selectedMinute,
                onValueChanged = {
                    onMinuteChanged(it)
                    onUpdateDate(Triple(selectedHour, selectedMinute, am_pm))
                },
                items = minutes,
                onUseFetch = true,
                onFetchTime = onFetchTime
            )
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

        val hours = (0..12).toList()
        val minutes = (0..55 step(5)).toList()
        var selectedHour by remember{ mutableStateOf(new_hour) }
        var selectedMinute by remember{ mutableStateOf(new_minute) }

        val hourFormatter = DateTimeFormatter.ofPattern("HH")
        val minuteFormatter = DateTimeFormatter.ofPattern("MM")
        val amPmFormatter = DateTimeFormatter.ofPattern("a")

        var showTimePicker by remember{ mutableStateOf(true) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = White),
            contentAlignment = Alignment.Center
        ){
            TimePickerDialog(
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
    }
}