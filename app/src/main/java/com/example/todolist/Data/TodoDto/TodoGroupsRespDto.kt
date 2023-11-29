package com.example.todolist.Data.TodoDto

import com.example.todolist.Data.DataClass.TodoGroup
import com.google.gson.annotations.SerializedName

data class TodoGroupsRespDto(
    @SerializedName("commonResponse")
    val commonResponse : String,
    @SerializedName("data")
    val data : List<TodoGroup>,
    @SerializedName("message")
    val message : String
)
