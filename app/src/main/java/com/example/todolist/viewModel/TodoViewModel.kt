package com.example.todolist.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.Data.SignInReqDto
import com.example.todolist.Data.SignUpReqDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository : UserRepository
) : ViewModel() {

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

            //로그인 후 처리 필요
            
        }
    }
}