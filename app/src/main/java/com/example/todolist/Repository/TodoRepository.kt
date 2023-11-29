package com.example.todolist.Module

import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoApi: TodoApi
){
    val TAG = "TodoRepository"

}