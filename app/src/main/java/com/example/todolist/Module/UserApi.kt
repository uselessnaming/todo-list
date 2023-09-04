package com.example.todolist.Module

import com.example.todolist.Data.LoginReqDto
import com.example.todolist.Data.LoginRespDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    //API interface
    @POST("/sign-in")
    fun login(@Body loginReqDto : LoginReqDto) : Call<LoginRespDto>

}