package com.example.todolist.Data

import com.google.gson.annotations.SerializedName

data class Todo(
    @SerializedName("checked")
    val checked : Boolean,
    @SerializedName("createdAt")
    val startDate : String,
    @SerializedName("deadDate")
    val endDate : String,
    @SerializedName("finishTodo")
    val isFinished : Boolean,
    @SerializedName("todoDescription")
    val description : String,
    @SerializedName("todoId")
    val todoId : Long,
    @SerializedName("user")
    val user : User
)