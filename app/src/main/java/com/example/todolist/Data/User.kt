package com.example.todolist.Data

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("accountNonExpired")
    val accountNonExpired : Boolean,
    @SerializedName("accountNonLocked")
    val accountNonLocked : Boolean,
    @SerializedName("authorities")
    val authorities : Authority,
    @SerializedName("createdAt")
    val createDate : String,
    @SerializedName("credentialNonExpired")
    val credentialNonExpired : Boolean,
    @SerializedName("enabled")
    val isEnabled : Boolean,
    @SerializedName("modifiedAt")
    val modifyDate : String,
    @SerializedName("roles")
    val role : ArrayList<String>,
//    @SerializedName("todoList")
//    val todoList : ArrayList<이건 뭐냐>
    @SerializedName("userEmail")
    val userEmail : String,
    @SerializedName("userId")
    val userId : Long,
    @SerializedName("username")
    val userName : String
)