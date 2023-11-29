package com.example.todolist.Module

import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoApi: TodoApi
){
    val TAG = "TodoRepository"

    fun getData(userId : Int) : TodoResponseBody {
        try{
            val call = todoApi.getTodos(userId)

            val response = call.execute()

            return if(response.isSuccessful) {
                response.body() ?: throw NullPointerException("Response body is NULL in ${TAG}")
            } else {
                throw Exception("서버 오류 : ${response.code()}")
            }
        } catch(e : Exception){
            throw IllegalArgumentException("에러 : ${e.message}")
        }
    }
}