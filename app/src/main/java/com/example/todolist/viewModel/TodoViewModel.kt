package com.example.todolist.Module

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.Data.SignInReqDto
import com.example.todolist.Data.SignUpReqDto
import com.example.todolist.Data.saveAuthToken
import com.example.todolist.Module.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository : UserRepository,
    @ApplicationContext private val context : Context
) : ViewModel() {
    val TAG = "TodoViewModel"

    private val _isLogin = MutableStateFlow(false)
    val isLogin : StateFlow<Boolean> = _isLogin

    //error msg 관리
    private val _errMsg = MutableStateFlow<String?>(null)
    val errMsg : StateFlow<String?> = _errMsg
    
    fun addUser(signUpReqDto : SignUpReqDto) {
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(signUpReqDto)
        }
    }

    fun login(signInReqDto : SignInReqDto){
        viewModelScope.launch(Dispatchers.IO){
            val result = repository.login(signInReqDto)

            Log.d(TAG,"result : ${result.result}")

            //로그인 후 처리 필요
            if (result.result == "SUCCESS"){
                _isLogin.value = true
                saveAuthToken(context, result.token)
            }
        }
    }
}