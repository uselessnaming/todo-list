package com.example.todolist.Module

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.Data.SignInReqDto
import com.example.todolist.Data.SignUpReqDto
import com.example.todolist.Data.saveAuthToken
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
    @ApplicationContext private val context : Context
) : ViewModel() {
    val TAG = "TodoViewModel"

    //로그인 상태 유지
    private val _isLogin = MutableStateFlow(false)
    val isLogin : StateFlow<Boolean> = _isLogin

    //error msg 관리
    private val _errMsg = MutableStateFlow<String?>(null)
    val errMsg : StateFlow<String?> = _errMsg
    
    suspend fun signUp(signUpReqDto : SignUpReqDto) =
        viewModelScope.async(Dispatchers.IO){
            val result = repository.signUp(signUpReqDto)

            return@async result.result
        }.await()

    suspend fun signIn(signInReqDto : SignInReqDto) =
        viewModelScope.async(Dispatchers.IO){
            val result = repository.signIn(signInReqDto)

            //로그인 성공 시
            if (result.result == "SUCCESS"){
                _isLogin.value = true
                saveAuthToken(context, result.token)
            }
            //로그인 실패 시
            else {
                _isLogin.value = false
            }
            return@async result.result
        }.await()

    //로그아웃
    fun logout(){
        _isLogin.value = false
    }
}