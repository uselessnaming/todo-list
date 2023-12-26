package com.example.todolist.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.Data.Calendar.MyDate
import com.example.todolist.Data.DataClass.TodoGroupInTodo
import com.example.todolist.Data.LoginDto.LoginRequestDto
import com.example.todolist.Data.LoginDto.User
import com.example.todolist.Data.Todo
import com.example.todolist.Data.TodoDto.TodoReqDto
import com.example.todolist.Data.readAuthToken
import com.example.todolist.Data.saveAuthToken
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
import java.text.SimpleDateFormat
import java.util.Locale
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
    val isLogin : StateFlow<Boolean>
        get() = _isLogin

    //로그인한 유저의 id 관리
    private val _id = MutableStateFlow(0)
    val id : StateFlow<Int>
        get() = _id

    //dayList
    private val _days = MutableStateFlow(listOf<MyDate>())
    val days : StateFlow<List<MyDate>>
        get() = _days

    //error msg 관리
    private val _errMsg = MutableStateFlow<String?>(null)
    val errMsg : StateFlow<String?> = _errMsg

    //현재 유저의 Todo List
    private val _todos = MutableStateFlow(mutableListOf<Todo>())
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
        _id.value = -1
        saveAuthToken(context, null)
    }

    fun getToday() = calendar.getToday()

    //로그인 테스트
    fun login(){
        runBlocking{
            viewModelScope.launch(Dispatchers.IO){
                val reqDto = LoginRequestDto(
                    clientId = "testtest",
                    clientPassword = "q1w2e3r4t5@"
                )
                val result = repository.login(reqDto)
                saveAuthToken(context, result.data)
            }
        }
    }

    //로그인 기능 x 테스트 용
    fun setClientNum(id : Int){
//        login()
        runBlocking{
            viewModelScope.launch(Dispatchers.IO){

                val reqDto = LoginRequestDto(
                    clientId = "testtest",
                    clientPassword = "q1w2e3r4t5@"
                )
                val result = repository.login(reqDto)
                saveAuthToken(context, result.data)

                _id.value = id
                val token = readAuthToken(context)

                Log.d(TAG, "login : ${token}")

                if (token == null){
                    _errMsg.tryEmit("로그인 정보가 없습니다.")
                } else {
                    _todoGroups.tryEmit(todoRepository.getGroups(id, token))
                }

                val tmpList = arrayListOf<Todo>()
                todoGroups.value.forEach{
                    tmpList.addAll(it.todoList)
                }

                _todos.tryEmit(tmpList)
                Log.d(TAG, "todos : ${todos.value}")
            }
            val tmp = mutableListOf<MyDate>()
            calendar.dayList.forEach{
                tmp.add(it)
            }
            _days.value = tmp
            Log.d(TAG, "days : ${days.value}")
        }
    }

    //id를 통해서 Todo 객체 가져오기
    fun getTodoById(id : String) : Todo {

        var result : Todo? = null

        todos.value.forEach{
            if (id.toInt() == it.todoNum){
                result = it
                return@forEach
            }
        }
        return result ?: throw NullPointerException("Error : Todo is NULL in getTodo() / ${TAG}")
    }

    //다음 주로 이동
    fun setNextWeek(){
        calendar.setNextWeek()

        val tmp = mutableListOf<MyDate>()
        calendar.dayList.forEach{
            tmp.add(it)
        }

        _days.value = tmp
    }

    //전 주로 이동
    fun setPrevWeek(){
        calendar.setPrevWeek()

        val tmp = mutableListOf<MyDate>()
        calendar.dayList.forEach{
            tmp.add(it)
        }

        _days.value = tmp
    }

    //selectedDate를 반환
    fun getSelectedDay() : MyDate{
        return calendar.selectedMyDate
    }

    //selectedDate를 설정
    fun setSelectedDay(date : MyDate){
        calendar.setDate(date)
    }

    //errMsg 초기화
    fun resetErrMsg(){
        _errMsg.value = null
    }

    //todo추가
    fun addTodo(
        startDate : String,
        endDate : String,
        description : String,
        title : String,
        location : String,
        todoGroup : TodoGroupInTodo
    ){
        val groupNum =  if (todoGroup.title == "No Group") -1 else {todoGroup.todoList[0].groupNum}
        runBlocking{
            viewModelScope.launch(Dispatchers.IO){
                val newReq = TodoReqDto(
                    endDate = endDate,
                    isFinished = false,
                    startDate = startDate,
                    desc = description,
                    groupNum = groupNum,
                    location = location,
                    title = title
                )
                val token = readAuthToken(context) ?: throw NullPointerException("Token Data is NULL on ${TAG} in AddTodo()")
                //data 추가
                todoRepository.addTodo(token, id.value, newReq)

                //data fetch
                _todoGroups.tryEmit(todoRepository.getGroups(id.value, token))
            }
        }
    }

    //데이터 갱신
    fun fetchTodos(date : String, todos : List<Todo>){
        runBlocking {
            val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA)
            val stdToday = dateFormat.parse(date) ?: throw NullPointerException("Today is NULL on ${TAG} in fatchTodos()")

            val result = mutableListOf<Todo>()
            todos.forEach{
                val day = dateFormat.parse(it.deadDate) ?: throw NullPointerException("Today is NULL on ${TAG} in fatchTodos()")
                if (day.compareTo(stdToday) > 0){
                    result.add(it)
                }
            }
            _todos.tryEmit(result)
        }
    }

    //todo group 추가
    fun addTodoGroup(
        title : String
    ){
        val token = readAuthToken(context) ?: throw NullPointerException("Token is NULL on ${TAG} in addTodoGroup")
        runBlocking{
            viewModelScope.launch(Dispatchers.IO){
                val resultMsg = todoRepository.addTodoGroup(token, id.value, title)
                if (resultMsg != "추가 성공") {
                    _errMsg.value = resultMsg
                }
                _todoGroups.tryEmit(todoRepository.getGroups(token = token, id = id.value))
            }
        }
    }

    //todo group 삭제
    fun delTodoGroup(todoGroup : TodoGroupInTodo){
        val token = readAuthToken(context) ?: throw NullPointerException("Token is NULL on ${TAG} in delTodoGroup")
        if (todoGroup.todoList[0].groupNum == -1){
            _errMsg.value == "No Group은 삭제 불가능합니다."
        } else {
            runBlocking{
                viewModelScope.launch(Dispatchers.IO){
                    /** 정아한테 groupNum을 받는 Api를 통해서 데이터를 변경해줘야 함 */
                    val resultMsg = todoRepository.delTodoGroup(token = token, id = id.value, todoGroupNum = todoGroup.todoList[0].groupNum)

                    if (resultMsg != "삭제 성공") {
                        _errMsg.value = resultMsg
                    }
                    _todoGroups.tryEmit(todoRepository.getGroups(id = id.value, token = token))
                }
            }
        }
    }

    //todo group update
    fun updateTodoGroup(todoGroup : TodoGroupInTodo){
        val token = readAuthToken(context) ?: throw NullPointerException("Token is NULL on ${TAG} in updateTodoGroup")
        if (todoGroup.todoList[0].groupNum == -1){
            _errMsg.value == "No Group은 수정 불가능합니다."
        } else {
            runBlocking{
                viewModelScope.launch(Dispatchers.IO){
                    /** 정아한테 groupNum을 받는 Api를 통해서 데이터를 변경해줘야 함 */
                    val resultMsg = todoRepository.updateTodoGroup(
                        token = token,
                        id = id.value,
                        todoGroupNum = todoGroup.todoList[0].groupNum,
                        title = todoGroup.title,
                        isImportant = todoGroup.isImportant
                    )

                    if (resultMsg != "수정 성공") {
                        _errMsg.value = resultMsg
                    }
                    _todoGroups.tryEmit(todoRepository.getGroups(id = id.value, token = token))
                }
            }
        }
    }
}