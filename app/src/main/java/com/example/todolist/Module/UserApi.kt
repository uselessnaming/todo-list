package com.example.todolist.Module

import com.example.todolist.Data.SignInReqDto
import com.example.todolist.Data.SignInRespDto
import com.example.todolist.Data.SignUpReqDto
import com.example.todolist.Data.SignUpRespDto
import com.example.todolist.Data.UserDto.GetUserRespDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    //로그인
    @POST("/sign-api/sign-in")
    fun signIn(@Body signInReqDto : SignInReqDto) : Call<SignInRespDto>

    //회원가입
    @POST("/sign-api/sign-up")
    fun signUp(@Body signUpReqDto : SignUpReqDto) : Call<SignUpRespDto>

    //중복확인

    //유저 이름 검색
    @GET("/users/{username}")
    fun getDataByUser(@Path("username") username : String) : Call<GetUserRespDto>

}