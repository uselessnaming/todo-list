package com.example.todolist.Data

import com.google.gson.annotations.SerializedName

data class SignInRespDto(
    @SerializedName("commonResponse")
    val result : String,
    @SerializedName("data")
    val token : String
)
