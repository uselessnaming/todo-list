package com.example.todolist.Module

import com.example.todolist.Data.DataClass.TodoGroupInTodo
import com.example.todolist.Data.Todo
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoApi: TodoApi
){
    val TAG = "TodoRepository"
    
    //id를 기반으로 Todo 정보들을 가져옴
    fun getTodos(id : Int) : List<Todo>{
        val todoList = ArrayList<Todo>()

        //Todo Group이 있는 Todo List들을 우선 삽입
        try{
            val result = todoApi.getAllTodosInGroup(id).execute()
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

    fun getGroups(id : Int) : List<TodoGroupInTodo>{
        val todoGroups = arrayListOf<TodoGroupInTodo>()

        //group이 있는 Todo
        try{
            val result = todoApi.getAllTodosInGroup(id).execute()
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
            val result = todoApi.getTodosNoGroup(id).execute()
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
            it.groupNum = 1000
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
}