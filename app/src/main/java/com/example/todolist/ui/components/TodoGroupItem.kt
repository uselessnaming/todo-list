package com.example.todolist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.Data.DataClass.TodoGroupInTodo
import com.example.todolist.Data.Todo
import com.example.todolist.ui.theme.TodoListTheme

@Composable
fun TodoGroupItem(
    todoGroup : TodoGroupInTodo
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ){
            Text(
                modifier = Modifier.weight(3f),
                text = todoGroup.title,
                fontSize = 28.sp,
                color = Color.Black,
            )

            Spacer(Modifier.width(10.dp))

            //수정
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit Button"
                )
            }

            Spacer(Modifier.width(10.dp))

            //삭제
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete Button"
                )
            }
        }
    }
}

@Preview
@Composable
fun TestTodoGroupItem(){
    TodoListTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            contentAlignment = Alignment.Center
        ){
            TodoGroupItem(
                todoGroup = TodoGroupInTodo(
                    title = "테스트용",
                    isImportant = false,
                    todoList = listOf<Todo>()
                )
            )
        }
    }
}