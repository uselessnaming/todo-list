package com.example.todolist.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.Data.DataClass.TodoGroupInTodo
import com.example.todolist.Data.LoginDto.User
import com.example.todolist.Data.Todo
import com.example.todolist.Module.MyCalendar
import com.example.todolist.Module.TodoRepository
import com.example.todolist.Module.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository : UserRepository,
    private val todoRepository: TodoRepository,
    @ApplicationContext private val context : Context
) : ViewModel() {
    val TAG = "TodoViewModel"

    //달력 모듈
    val calendar = MyCalendar()

    private val _days = MutableStateFlow(listOf<Int>())
    val days : StateFlow<List<Int>>
        get() = _days

    //로그인 상태 유지
    private val _isLogin = MutableStateFlow(false)
    val isLogin : StateFlow<Boolean>
        get() = _isLogin

    //로그인한 유저의 id 관리
    private val _id = MutableStateFlow(0)
    val id : StateFlow<Int>
        get() = _id

    //error msg 관리
    private val _errMsg = MutableStateFlow<String?>(null)
    val errMsg : StateFlow<String?> = _errMsg

    //현재 유저의 Todo List
    private val _todos = MutableStateFlow(listOf<Todo>())
    val todos : StateFlow<List<Todo>>
        get() = _todos

    //현재 유저의 Todo Group List
    private val _todoGroups = MutableStateFlow(listOf<TodoGroupInTodo>())
    val todoGroups : StateFlow<List<TodoGroupInTodo>>
        get() = _todoGroups

    suspend fun signUp(signUpReqDto : User) =
        viewModelScope.async(Dispatchers.IO){
            val result = repository.signUp(signUpReqDto)

            return@async result.result
        }.await()

    //로그아웃
    fun logout(){
        _isLogin.value = false
        _id.value = 0
    }

    fun getToday() = calendar.getToday()

    //로그인 기능 x 테스트 용
    fun setClientNum(id : Int){
        runBlocking{
            viewModelScope.launch(Dispatchers.IO){
                _id.value = id
                _todoGroups.tryEmit(todoRepository.getGroups(id))
                Log.d(TAG, "_todosGroups : ${_todoGroups.value}")


                val tmpList = arrayListOf<Todo>()
                todoGroups.value.forEach{
                    tmpList.addAll(it.todoList)
                }

                _todos.tryEmit(tmpList)
            }
        }
    }

    //id를 통해서 Todo 객체 가져오기
    fun getTodoById(id : String) : Todo {

        var result : Todo? = null

        Log.d(TAG, "todos : ${todos.value}")
        todos.value.forEach{
            if (id.toInt() == it.todoNum){
                result = it
                return@forEach
            }
        }
        return result ?: throw NullPointerException("Error : Todo is NULL in getTodo() / ${TAG}")
    }
}