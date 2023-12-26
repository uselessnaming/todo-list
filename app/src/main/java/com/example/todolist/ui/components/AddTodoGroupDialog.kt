package com.example.todolist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.todolist.ui.theme.TodoListTheme

@Composable
fun AddTodoGroupDialog(
    onDismissRequest : () -> Unit,
    addGroup : () -> Unit,
    width : Dp,
    height : Dp,
) {
    var groupTitle by remember{mutableStateOf("")}
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .width(width)
                .height(height)
                .background(color = Color.White, shape = RoundedCornerShape(25.dp))
                .padding(vertical = 5.dp, horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "그룹 추가",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.weight(1f))

            TextField(
                value = groupTitle,
                onValueChange = {
                    groupTitle = it
                },
                placeholder = {
                    Text(
                        text = "그룹 이름을 입력해주세요"
                    )
                }
            )

            Spacer(Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text(
                        text = "취소",
                        fontSize = 20.sp
                    )
                }

                Spacer(Modifier.width(40.dp))

                Button(
                    onClick = {
                        addGroup()
                        onDismissRequest()
                    }
                ) {
                    Text(
                        text = "추가",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TestAddTodoGroupDialog(){
    TodoListTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            contentAlignment = Alignment.Center
        ){
            var showDialog by remember{mutableStateOf(true)}
            val configuration = LocalConfiguration.current
            val width = configuration.screenWidthDp.dp
            val height = configuration.screenHeightDp.dp
            AddTodoGroupDialog(
                onDismissRequest = {
                    showDialog = !showDialog
                },
                width = (width / 5) * 4,
                height = (height / 3),
                addGroup = {}
            )
        }
    }
}