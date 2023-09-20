package com.example.todolist.Data

import com.google.gson.annotations.SerializedName

data class SignUpRespDto(
    @SerializedName("commonResponse")
    val result : String,
    @SerializedName("data")
    val data : String
)
