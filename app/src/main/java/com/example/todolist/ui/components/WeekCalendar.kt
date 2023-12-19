package com.example.todolist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.ui.theme.TodoListTheme
import java.time.LocalDate
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeekCalendar(
    modifier : Modifier = Modifier,
    value : Int,
    onValueChanged : (Int) -> Unit,
    items : List<Int>,
    onSlideNext : (Float) -> Unit,
    onSlidePrev : (Float) -> Unit
){
    //day list
    val dayList = listOf("일","월","화","수","목","금","토")

    //offset
    var offsetX by remember{mutableStateOf(0f)}
    //방향
    var direction by remember{mutableStateOf(-1)}

    Row(
        modifier = modifier
            .pointerInput(Unit){
                detectDragGestures (
                    onDrag = { change, dragAmount ->
                        change.consume()
                        val (x,y) = dragAmount
                        if (abs(x) > abs(y)) {
                            when{
                                //right
                                x > 0 -> {
                                    offsetX += x
                                    direction = 0
                                }
                                //left
                                x < 0 -> {
                                    offsetX += x
                                    direction = 1
                                }
                            }
                        }
                    },
                    onDragEnd = {
                        when(direction){
                            0 -> {
                                if(offsetX > 300){
                                    // right 이벤트
                                    onSlideNext(offsetX)
                                }
                                offsetX = 0f
                            }
                            1 -> {
                                if (offsetX < -300){
                                    onSlidePrev(offsetX)
                                }
                                offsetX = 0f
                            }
                        }
                    }
                )
            }
    ){
        for (i in 0..6){

            //선택 확인
            val isSelectedDay = value == items[i]

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onValueChanged(items[i])
                    }
            ){

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = dayList[i],
                    fontSize = 20.sp,
                    color = Black,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(5.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp)
                        .aspectRatio(1f),
                ){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "0",
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight(600)
                        )
                    }
                }

                Spacer(Modifier.height(5.dp))

                Text(
                    modifier = Modifier.fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = if(isSelectedDay) Black else Color.Unspecified,
                            shape = CircleShape
                        ),
                    text = "${items[i]}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(500),
                    color = Black,
                    textAlign = TextAlign.Center
                )
            }

            if (i != 6){
                Spacer(Modifier.width(5.dp))
            }
        }
    }
}

@Preview
@Composable
fun TestWeekCalendar(){
    TodoListTheme {

        var test by remember{mutableStateOf("test")}
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = White),
            verticalArrangement = Arrangement.Center
        ){
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = test,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                val items = (10..50).toList()
                var year by remember {mutableStateOf(LocalDate.now().year) }

                WeekCalendar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    value = year,
                    onValueChanged = {
                        year = it
                    },
                    items = items,
                    onSlideNext = {
                        test = "next / offset is ${it}"
                    },
                    onSlidePrev = {
                        test = "prev / offset is ${it}"
                    }
                )
            }
        }
    }
}