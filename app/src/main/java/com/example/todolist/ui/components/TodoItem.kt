package com.example.todolist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.ui.theme.TodoListTheme
import com.example.todolist.ui.theme.Yellow

@Composable
fun TodoItem(
    modifier : Modifier = Modifier,

){
    val title = "title"
    val startDate = "09월 07일"
    val endDate = "09월 10일"

    Row(
        modifier = modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        //title
        Text(
            modifier = Modifier.weight(6f),
            text = title,
            fontSize = 26.sp,
            color = Black,
            fontWeight = FontWeight(500)
        )

        Text(
            modifier = Modifier.weight(3f),
            text = "$startDate ~\n$endDate",
            fontSize = 16.sp,
            color = Black,
            fontWeight = FontWeight(400)
        )

        IconButton(
            modifier = Modifier.size(26.dp),
            onClick = {
            }
        ) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = Icons.Filled.Star,
                contentDescription = "isImportant",
                tint = Yellow,
            )
        }
    }
}

@Preview
@Composable
fun TestTodoItem(){
    TodoListTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = White)
        ){
            TodoItem(
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}