package com.example.todolist.ui.screens.drawerMenu.menus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDrawer(
    items : List<ImageVector> = listOf(),
    selectedMenu : ImageVector = Icons.Filled.Home,
    drawerState : DrawerState = rememberDrawerState(DrawerValue.Closed),
    onClick : () -> Unit = {}
){
    val coroutineScope = rememberCoroutineScope()

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Spacer(Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(color = Color.Transparent, shape = CircleShape),
            contentAlignment = Alignment.Center
        ){
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = Icons.Filled.Person,
                contentDescription = "Profile Icons"
            )
        }

        Spacer(Modifier.height(25.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "환영합니다.",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
        )
        items.forEach{ item ->
            if (item != Icons.Filled.Home){
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
//                        selectedMenu = item
                    }
                )
            }
        }
    }
}