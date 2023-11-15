package com.example.todolist.Data.UserDto

import com.google.gson.annotations.SerializedName

data class AddTodoReqDto(
    @SerializedName("deadDate")
    val deadDate: String,
    @SerializedName("isFinished")
    val isFinished: Boolean,
    @SerializedName("isImportant")
    val isImportant: Boolean,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("todoDescription")
    val todoDescription: String,
    @SerializedName("todoTitle")
    val todoTitle: String
)