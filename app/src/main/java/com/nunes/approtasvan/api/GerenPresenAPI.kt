package com.nunes.approtasvan.api

import com.nunes.approtasvan.model.GerenPresen
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface GerenPresenAPI {

    @POST("gerencia-presenca")
    fun saveGerenPresen(@Body gerenPresen: GerenPresen):Call<GerenPresen>

}