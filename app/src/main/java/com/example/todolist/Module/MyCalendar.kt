package com.example.todolist.Module

import com.example.todolist.Data.Calendar.MyDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

class MyCalendar {
    
    val calendar = Calendar.getInstance()

    //오늘의 날짜 미리 가져오기
    val today = MyDate(
        calendar.get(Calendar.YEAR), //년도
        calendar.get(Calendar.MONTH) + 1, //월
        calendar.get(Calendar.DAY_OF_MONTH), //일
        calendar.get(Calendar.DAY_OF_WEEK) //요일
    )

    //현재 선택된 날짜
    var selectedMyDate : MyDate

    var year : Int
    var month : Int
    var day : Int
    var day_of_week : Int
    val dayList : MutableList<MyDate>

    //초기화 시 오늘 날짜로
    init{
        year = today.year
        month = today.month //month는 1~12 범위
        day = today.day
        day_of_week = today.day_of_week

        selectedMyDate = MyDate(year, month, day, day_of_week)
        dayList = arrayListOf()

        val myDate = MyDate(year, month, day, day_of_week)
        for (i:Int in 1..7) {
            val diff = i - day_of_week
            dayList.add(calDate(myDate, diff))
        }
    }

    //날짜를 계산하는 함수
    private fun calDate(myDate : MyDate, diff : Int) : MyDate{
        val calendar = Calendar.getInstance()
        calendar.set(myDate.year, myDate.month-1, myDate.day)
        calendar.add(Calendar.DAY_OF_WEEK, diff)

        return MyDate(
            year = calendar.get(Calendar.YEAR),
            month = calendar.get(Calendar.MONTH)+1,
            day = calendar.get(Calendar.DAY_OF_WEEK),
            day_of_week = myDate.day_of_week
        )
    }

    //날짜 변경
    fun setDate(myDate : MyDate){
        selectedMyDate = myDate
    }

    //다움 주로 이동
    fun setNextWeek(){
        //dayList를 초기화
        dayList.clear()
        
        //다음 주로 이동하면 현재 날짜 + 일주일
        val calendar = Calendar.getInstance()
        calendar.set(selectedMyDate.year, selectedMyDate.month-1, selectedMyDate.day)
        calendar.add(Calendar.DAY_OF_WEEK, 7)

        for (i:Int in 1..7) {
            val diff = i - day_of_week
            dayList.add(calDate(selectedMyDate, diff))
        }
    }

    //이전 주로 이동
    fun setPrevWeek(){
        //dayList를 초기화
        dayList.clear()
        
        //이전 주로 이동하면 현재 날짜 - 일주일
        val calendar = Calendar.getInstance()
        calendar.set(selectedMyDate.year, selectedMyDate.month-1, selectedMyDate.day)
        calendar.add(Calendar.DAY_OF_WEEK, -7)

        for (i:Int in 1..7) {
            val diff = i - day_of_week
            dayList.add(calDate(selectedMyDate, diff))
        }
    }

    //오늘 날짜를 받는 함수
    //List[0] = 날짜 / List[2] = 시간 / List[3] = 분
    fun getToday() : List<String>{
        val returnList = mutableListOf<String>()

        val todayDate = LocalDateTime.now()

        //날짜 출력 format
        val dateFormat = DateTimeFormatter.ofPattern("yyyy. MM. dd")
        returnList.add(dateFormat.format(todayDate))

        //시간 출력 format
        val timeFormat = DateTimeFormatter.ofPattern("HH")
        returnList.add(timeFormat.format(todayDate))

        //분 출력 format
        val minuteFormat = DateTimeFormatter.ofPattern("mm")
        returnList.add(minuteFormat.format(todayDate))

        return returnList
    }
}