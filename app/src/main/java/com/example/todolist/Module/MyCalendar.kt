package com.example.todolist.Module

import com.example.todolist.Data.Calendar.Date
import java.text.SimpleDateFormat
import java.util.Calendar

class MyCalendar {
    
    val calendar = Calendar.getInstance()

    //오늘의 날짜 미리 가져오기
    val today = Date(
        calendar.get(Calendar.YEAR), //년도
        calendar.get(Calendar.MONTH) + 1, //월
        calendar.get(Calendar.DAY_OF_MONTH), //일
        calendar.get(Calendar.DAY_OF_WEEK) //요일
    )

    //현재 선택된 날짜
    var selectedDate : Date

    var year : Int
    var month : Int
    var day : Int
    var day_of_week : Int
    val dayList : MutableList<Date>

    //초기화 시 오늘 날짜로
    init{
        year = today.year
        month = today.month //month는 1~12 범위
        day = today.day
        day_of_week = today.day_of_week

        selectedDate = Date(year, month, day, day_of_week)
        dayList = arrayListOf()

        val date = Date(year, month, day, day_of_week)
        for (i:Int in 1..7) {
            val diff = i - day_of_week
            dayList.add(calDate(date, diff))
        }
    }

    //날짜를 계산하는 함수
    private fun calDate(date : Date, diff : Int) : Date{
        val calendar = Calendar.getInstance()
        calendar.set(date.year, date.month-1, date.day)
        calendar.add(Calendar.DAY_OF_WEEK, diff)

        return Date(
            year = calendar.get(Calendar.YEAR),
            month = calendar.get(Calendar.MONTH)+1,
            day = calendar.get(Calendar.DAY_OF_WEEK),
            day_of_week = date.day_of_week
        )
    }

    //날짜 변경
    fun setDate(date : Date){
        selectedDate = date
    }

    //다움 주로 이동
    fun setNextWeek(){
        //dayList를 초기화
        dayList.clear()
        
        //다음 주로 이동하면 현재 날짜 + 일주일
        val calendar = Calendar.getInstance()
        calendar.set(selectedDate.year, selectedDate.month-1, selectedDate.day)
        calendar.add(Calendar.DAY_OF_WEEK, 7)

        for (i:Int in 1..7) {
            val diff = i - day_of_week
            dayList.add(calDate(selectedDate, diff))
        }
    }

    //이전 주로 이동
    fun setPrevWeek(){
        //dayList를 초기화
        dayList.clear()
        
        //이전 주로 이동하면 현재 날짜 - 일주일
        val calendar = Calendar.getInstance()
        calendar.set(selectedDate.year, selectedDate.month-1, selectedDate.day)
        calendar.add(Calendar.DAY_OF_WEEK, -7)

        for (i:Int in 1..7) {
            val diff = i - day_of_week
            dayList.add(calDate(selectedDate, diff))
        }
    }

    //오늘 날짜를 받는 함수
    //List[0] = 날짜 / List[2] = 시간 / List[3] = 분
    fun getToday() : List<String>{
        val returnList = mutableListOf<String>()

        //날짜 출력 format
        val dateFormat = SimpleDateFormat("yyyy. MM. dd")
        returnList.add(dateFormat.format(today))

        val calendarTime = calendar.time

        //시간 출력 format
        val timeFormat = SimpleDateFormat("HH")
        returnList.add(timeFormat.format(calendarTime))

        //분 출력 format
        val minuteFormat = SimpleDateFormat("mm")
        returnList.add(minuteFormat.format(calendarTime))

        return returnList
    }
}