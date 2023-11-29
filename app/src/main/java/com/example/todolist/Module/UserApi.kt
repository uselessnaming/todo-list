package com.example.todolist.Module

import com.example.todolist.Data.LoginDto.DelUserRespDto
import com.example.todolist.Data.LoginDto.GetAllRespDto
import com.example.todolist.Data.LoginDto.UpdateUserReqDto
import com.example.todolist.Data.LoginDto.User
import com.example.todolist.Data.SignUpRespDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApi {

    //유저 전체 조회
    @GET("/clients")
    fun getAllUsers() : Call<GetAllRespDto>

    //유저 회원 정보 수정
    @PUT("/clients/{clientnum}")
    fun updateUser(@Path("clientnum") clientNum : Int,@Body signUpReqDto : UpdateUserReqDto) : Call<SignUpRespDto>

    //회원 가입
    @POST("/clients/sign-up")
    fun addUser(@Body requestClientDto : User) : Call<SignUpRespDto>

    //회원 탈퇴
    @DELETE("clients/{clientnum}")
    fun deleteUser(@Path("clientnum") clientNum : Int) : Call<DelUserRespDto>
}