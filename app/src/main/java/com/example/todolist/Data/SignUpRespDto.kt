package com.example.todolist.Data

import com.google.gson.annotations.SerializedName

data class SignUpRespDto(
    @SerializedName("todoList")
    val todos : ArrayList<Todo>,
    @SerializedName("userEmail")
    val userEmail : String,
    @SerializedName("userId")
    val userId : Long,
    @SerializedName("username")
    val userName : String
)
