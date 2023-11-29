package com.example.todolist.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    //로그인 상태 유지
    private val _isLogin = MutableStateFlow(false)
    val isLogin : StateFlow<Boolean> = _isLogin

    //로그인한 유저의 id 관리
    private val _id = MutableStateFlow(0)
    val id : StateFlow<Int> = _id

    //error msg 관리
    private val _errMsg = MutableStateFlow<String?>(null)
    val errMsg : StateFlow<String?> = _errMsg

    //현재 유저의 Todo List
    private val _todos = MutableStateFlow(listOf<Todo>())
    val todos : StateFlow<List<Todo>> = _todos

    suspend fun signUp(signUpReqDto : User) =
        viewModelScope.async(Dispatchers.IO){
            val result = repository.signUp(signUpReqDto)

            return@async result.result
        }.await()

//    suspend fun signIn(signInReqDto : SignInReqDto) =
//        viewModelScope.async(Dispatchers.IO){
//            val result = repository.signIn(signInReqDto)
//
//            //로그인 성공 시
//            if (result.result == "SUCCESS"){
//                _isLogin.value = true
//                saveAuthToken(context, result.token)
//                //userId의 값을 설정 _id.value = 0
//                Log.d(TAG, "Token : ${result.token}")
//            }
//            //로그인 실패 시
//            else {
//                _isLogin.value = false
//            }
//            return@async result.result
//        }.await()

    //로그아웃
    fun logout(){
        _isLogin.value = false
        _id.value = 0
    }

    fun getToday() = calendar.getToday()
}