package com.example.todolist.Module

import com.example.todolist.Data.SignInReqDto
import com.example.todolist.Data.SignInRespDto
import com.example.todolist.Data.SignUpReqDto
import com.example.todolist.Data.SignUpRespDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    //로그인
    @POST("sign-in")
    suspend fun login(@Body signInReqDto : SignInReqDto) : Call<SignInRespDto>

    //회원가입
    @POST("sign-api/sign-up")
    suspend fun addUser(@Body signUpReqDto : SignUpReqDto) : Call<SignUpRespDto>
}