package com.example.todolist.Data

import com.google.gson.annotations.SerializedName

data class SignInReqDto(
    @SerializedName("password")
    val password : String,
    @SerializedName("username")
    val id : String
)