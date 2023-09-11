package com.example.todolist.module

import com.example.todolist.Data.SignInReqDto
import com.example.todolist.Data.SignInRespDto
import com.example.todolist.Data.SignUpReqDto
import com.example.todolist.Data.SignUpRespDto
import com.example.todolist.Module.UserApi
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val userApi : UserApi
) {
    suspend fun addUser(signUpReqDto: SignUpReqDto) : SignUpRespDto {
        val call = userApi.addUser(signUpReqDto)

        val response = call.execute()

        //error 코드를 받아야 함
        return if(response.isSuccessful) {
            when(response.code()){
                201 -> response.body() ?: throw NullPointerException("응답 데이터가 없습니다.")
                else -> throw Exception("${response.code()} : 오류")
            }
        } else {
            throw Exception("서버 오류 : ${response.code()}")
        }
    }

    suspend fun login(signInReqDto : SignInReqDto) : SignInRespDto {
        val call = userApi.login(signInReqDto)

        val response = call.execute()

        return if(response.isSuccessful){
            when(response.code()){
                201 -> response.body() ?: throw NullPointerException("Data is NULL")
                else -> throw Exception("${response.code()} : 오류")
            }
        } else {
            throw Exception("서버 오류 : ${response.code()}")
        }
    }
}