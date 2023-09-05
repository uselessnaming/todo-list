package com.example.todolist.Data

import com.google.gson.annotations.SerializedName

data class SingUpReqDto(
    @SerializedName("password")
    val password : String,
    @SerializedName("role")
    val role : ArrayList<String>,
    @SerializedName("userEmail")
    val email : String,
    @SerializedName("username")
    val userName : String
)
