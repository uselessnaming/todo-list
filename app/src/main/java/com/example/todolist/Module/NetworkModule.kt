package com.example.todolist.Module

import com.example.todolist.Data.SignInRespDto
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val USER_URL = "http://ec2-3-36-184-17.ap-northeast-2.compute.amazonaws.com:8080"

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(USER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideUserApi(retrofit : Retrofit) : UserApi{
        return retrofit.create(UserApi::class.java)
    }
}