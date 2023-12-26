package com.example.todolist.Module

import com.example.todolist.Data.DataClass.TodoGroupInTodo
import com.example.todolist.Data.Todo
import com.example.todolist.Data.TodoDto.TodoGroupReqDto
import com.example.todolist.Data.TodoDto.TodoReqDto
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoApi: TodoApi
){
    val TAG = "TodoRepository"
    
    //id를 기반으로 Todo 정보들을 가져옴
    fun getTodos(token: String, id : Int) : List<Todo>{
        val todoList = ArrayList<Todo>()

        //Todo Group이 있는 Todo List들을 우선 삽입
        try{
            val result = todoApi.getAllTodosInGroup(token, id).execute()
            if (result.isSuccessful){
                val response = result.body() ?: throw NullPointerException("Error : Data is NULL in ${TAG} / getTodos()")
                val data = response.data

                data.forEach{
                    todoList.addAll(it.todoList)
                }
            }
        } catch (e : Exception){
            throw IllegalArgumentException(e.message)
        }

        //Todo Group이 없는 Todo List들 삽입
        return todoList
    }

    fun getGroups(id : Int, token : String) : List<TodoGroupInTodo>{
        val todoGroups = arrayListOf<TodoGroupInTodo>()

        //group이 있는 Todo
        try{
            val result = todoApi.getAllTodosInGroup(token, id).execute()
            if (result.isSuccessful){
                val response = result.body() ?: throw NullPointerException("Error : Data is NULL in getGroups() / ${TAG}")

                todoGroups.addAll(response.data)
            } else {
                throw IllegalArgumentException("Error : ${result.code()}\n${result.errorBody()}")
            }
        } catch(e : Exception){
            throw IllegalArgumentException(e.message)
        }

        val noTitleTodos = arrayListOf<Todo>()

        //group이 없는 todo
        try{
            val result = todoApi.getTodosNoGroup(token, id).execute()
            if (result.isSuccessful){
                val response = result.body() ?: throw NullPointerException("Error : Data is NULL in ${TAG} / getTodos()")
                val data = response.data

                noTitleTodos.addAll(data)
            } else {
                throw IllegalArgumentException("Error : ${result.code()}\n${result.errorBody()}")
            }
        } catch (e : Exception){
            throw IllegalArgumentException(e.message)
        }

        noTitleTodos.forEach{
            it.groupNum = -1
        }

        //원래의 데이터 + noTitleTodos 추가
        val noTitleGroup = TodoGroupInTodo(
            todoList = noTitleTodos,
            title = "No Group",
            isImportant = false
        )
        todoGroups.add(noTitleGroup)

        return todoGroups
    }

    //Todo 추가
    fun addTodo(token : String, clientNum : Int, todoReq : TodoReqDto) : String{
        try {
            val result = todoApi.addTodo(token, clientNum, todoReq).execute()

            return if (result.isSuccessful){
                "추가 성공"
            } else {
                "Error : ${result.code()}\n${result.errorBody()}"
            }
        } catch (e : Exception){
            throw IllegalArgumentException(e.message)
        }
    }

    //todo group 추가
    fun addTodoGroup(token : String, id : Int, title : String) : String {
        try{
            val newReqDto = TodoGroupReqDto(
                groupName = title,
                isImportant = false
            )

            val result = todoApi.addTodoGroup(token, clientNum = id, todoGroupReqDto = newReqDto).execute()

            return if (result.isSuccessful){
                "추가 성공"
            } else {
                "Error : ${result.code()}\n${result.errorBody()}"
            }
        } catch (e : Exception){
            throw IllegalArgumentException(e.message)
        }
    }
}