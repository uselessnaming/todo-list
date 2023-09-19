package com.example.todolist.Module

import android.util.Log
import com.example.todolist.Data.SignInReqDto
import com.example.todolist.Data.SignInRespDto
import com.example.todolist.Data.SignUpReqDto
import com.example.todolist.Data.SignUpRespDto
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val userApi : UserApi
) {
    val TAG = "UserRepository"

    suspend fun addUser(signUpReqDto: SignUpReqDto) : SignUpRespDto {
        val call = userApi.signUp(signUpReqDto)

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
        val call = userApi.signIn(signInReqDto)

        Log.d(TAG,"call : ${call}")

        val response = call.execute()

        Log.d(TAG,"response : ${response}")

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