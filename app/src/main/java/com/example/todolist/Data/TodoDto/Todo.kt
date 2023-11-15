package com.example.todolist.Data

import com.google.gson.annotations.SerializedName

data class Todo(
    @SerializedName("startDate")
    val startDate : String,
    @SerializedName("deadDate")
    val deadDate : String,
    @SerializedName("isFinished")
    val isFinished : Boolean,
    @SerializedName("todoTitle")
    val title : String,
    @SerializedName("isImportant")
    val isImportant : Boolean
)