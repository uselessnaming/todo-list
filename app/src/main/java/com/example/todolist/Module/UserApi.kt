package com.example.todolist.Module

import com.example.todolist.Data.SignInReqDto
import com.example.todolist.Data.SignInRespDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

<<<<<<< Updated upstream
    //로그인
    @POST("/sign-api/sign-in")
    fun login(@Body loginReqDto : LoginReqDto) : Call<LoginRespDto>
=======
    //API interface
    @POST("/sign-in")
    fun login(@Body signinReqDto : SignInReqDto) : Call<SignInRespDto>
>>>>>>> Stashed changes

//    @GET("/users")
//    fun getAllUsers() : Call<>

}