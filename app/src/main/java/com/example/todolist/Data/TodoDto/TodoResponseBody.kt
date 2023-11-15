package com.example.todolist.Data.TodoDto

import com.example.todolist.Data.Todo
import com.google.gson.annotations.SerializedName

data class TodoResponseBody(
    @SerializedName("commonResponse")
    val commonResponse: String,
    @SerializedName("data")
    val data: List<Todo>
)