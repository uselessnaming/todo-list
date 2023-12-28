package com.example.todolist.Module

import com.example.todolist.Data.LoginDto.CommonUserRespDto
import com.example.todolist.Data.LoginDto.LoginRequestDto
import com.example.todolist.Data.LoginDto.SignUpReqDto
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val userApi : UserApi
) {
    val TAG = "UserRepository"

    //회원가입
    fun signUp(signUpReqDto: SignUpReqDto) : String {
        try{
            val response = userApi.addUser(signUpReqDto).execute()
            
            return if(response.isSuccessful) {
                val result = response.body() ?: throw NullPointerException("응답 데이터가 없습니다.")
                when(response.code()){
                    200 -> result.message
                    201 -> "회원가입 성공"
                    400 -> "유효성 검사 실패"
                    409 -> "아이디가 중복되었습니다"
                    else -> throw IllegalArgumentException("${response.code()} : 오류")
                }
            } else {
                throw Exception("서버 오류 : ${response.code()}")
            }
        } catch(e : Exception){
            throw IllegalArgumentException("Error : ${e.message}")
        }
    }

    //로그인
    fun login(loginRequestDto: LoginRequestDto) : CommonUserRespDto {
        try {
            val call = userApi.login(loginRequestDto)

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