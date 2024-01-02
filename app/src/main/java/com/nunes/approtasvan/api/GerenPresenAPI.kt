package com.nunes.approtasvan.api

import com.nunes.approtasvan.model.GerenPresen
import com.nunes.approtasvan.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GerenPresenAPI {

    @POST("gerencia-presenca")
    fun saveGerenPresen(@Body gerenPresen: GerenPresen):Call<GerenPresen>

    @GET("gerencia-presenca/{data}")
    fun getAlunosPresnetes(@Path("data") data:String):Call<List<User>>

}