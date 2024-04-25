package com.example.restapi14.retrofit

import com.example.restapi14.models.MyTodo
import com.example.restapi14.models.PostTodoRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyRetrofitApi {

    @GET("plan")
    fun getAllTodo(): Call<List<MyTodo>>

    @POST("plan/")
    fun addTodo(@Body postTodoRequest: PostTodoRequest):Call<MyTodo>
}