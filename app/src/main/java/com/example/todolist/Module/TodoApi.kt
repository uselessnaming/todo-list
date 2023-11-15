package com.example.todolist.Module

import com.example.todolist.Data.TodoDto.TodoResponseBody
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path

interface TodoApi {

    @POST("/todos/all/{userid}")
    fun getTodos(@Path("userid") userId : Int) : Call<TodoResponseBody>


}