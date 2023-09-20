package com.example.todolist.Module

import com.example.todolist.Data.SignInReqDto
import com.example.todolist.Data.SignInRespDto
import com.example.todolist.Data.SignUpReqDto
import com.example.todolist.Data.SignUpRespDto
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val userApi : UserApi
) {
    val TAG = "UserRepository"

    //회원가입
    fun signUp(signUpReqDto: SignUpReqDto) : SignUpRespDto {
        try{
            val call = userApi.signUp(signUpReqDto)

            val response = call.execute()
            
            return if(response.isSuccessful) {
                when(response.code()){
                    200 -> response.body() ?: throw NullPointerException("응답 데이터가 없습니다.")
                    400 -> response.body() ?: throw NullPointerException("응답 데이터가 없습니다.")
                    else -> throw IllegalArgumentException("${response.code()} : 오류")
                }
            } else {
                throw Exception("서버 오류 : ${response.code()}")
            }
        } catch(e : Exception){
            throw IllegalArgumentException("에러 : ${e.message}")
        }
    }

    //로그인
    fun signIn(signInReqDto : SignInReqDto) : SignInRespDto {
        try {
            val call = userApi.signIn(signInReqDto)

            val response = call.execute()
            return if (response.isSuccessful) {
                when (response.code()) {
                    200 -> response.body() ?: throw NullPointerException("Data is NULL")
                    else -> throw NullPointerException("서버 오류 : ${response.code()}")
                }
            } else {
                throw Exception("서버 오류 : ${response.code()}")
            }
        } catch(e : Exception){
            throw IllegalArgumentException("서버 오류 : ${e.message}")
        }
    }
}