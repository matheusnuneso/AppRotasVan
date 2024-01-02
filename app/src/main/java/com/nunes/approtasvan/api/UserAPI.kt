package com.nunes.approtasvan.api

import com.nunes.approtasvan.model.AuthUser
import com.nunes.approtasvan.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserAPI {

    @POST("users/auth")
    fun authUser(@Body authUser: AuthUser):Call<User>

    @GET("users/alunos")
    fun getAlunos():Call<List<User>>
}