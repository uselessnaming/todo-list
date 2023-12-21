package com.example.todolist.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Spinner(
    modifier : Modifier = Modifier,
    value : Int,
    onValueChanged : (Int) -> Unit,
    items : List<Int>,
    onUseFetch : Boolean = false,
    onFetchTime : () -> Unit = {},
){
    //lazy scroll state 저장 정보
    val scrollState = rememberLazyListState(initialFirstVisibleItemIndex = items.indexOf(value))

    //spinner 상태 변수
    var isExpanded by remember{mutableStateOf(false)}
    val onDropdownMenu = {
        isExpanded = !isExpanded
    }

    //coroutin
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = modifier
            .padding(horizontal = 5.dp)
            .clickable {
                onDropdownMenu()
            },
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            modifier = Modifier.clickable{
                    if (onUseFetch){
                        onFetchTime()
                    }
                    onDropdownMenu()
                },
            text = "${value}",
            fontSize = 18.sp,
            color = Black,
            textAlign = TextAlign.Center
        )

        DropdownMenu(
            modifier = Modifier,
            expanded = isExpanded,
            onDismissRequest = onDropdownMenu,
        ) {
            LazyColumn(
                modifier = modifier
                    .padding(horizontal = 5.dp)
                    .height(120.dp),
                state = scrollState
            ){
                items(items){
                    DropdownMenuItem(
                        modifier = Modifier.border(width = 0.5.dp, color = if(value == it) Black else Transparent),
                        text = {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(30.dp),
                                text = "${it}",
                                fontSize = 18.sp
                            )
                        },
                        onClick = {
                            coroutineScope.launch(Dispatchers.Main){
                                scrollState.animateScrollToItem(items.indexOf(it))
                            }
                            onValueChanged(it)
                        }
                    )
                }
            }
        }
    }
}