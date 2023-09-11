package com.example.todolist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawer(
    drawerState : DrawerState,
    onMenuItemClick : (Int) -> Unit,
){
    val items = listOf(
        Icons.Filled.Person,
        Icons.Filled.Settings
    )

    val coroutineScope = rememberCoroutineScope()
    var selectedMenu by remember{mutableStateOf(items[0])}

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet{
                Spacer(Modifier.height(10.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "환영합니다.",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                )
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(color = Transparent, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Profile Icons"
                    )
                }
                items.forEach{ item ->
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                imageVector = item,
                                contentDescription = "Menu Icons"
                            )
                        },
                        label = {
                            Text(
                                text = "sub menu"
                            )
                        },
                        selected = item == selectedMenu,
                        onClick = {
                            coroutineScope.launch(Dispatchers.Main){
                                drawerState.close()
                            }
                            selectedMenu = item
                        }
                    )
                }
            }
        }
    ) {
        when(selectedMenu){

        }
    }
}