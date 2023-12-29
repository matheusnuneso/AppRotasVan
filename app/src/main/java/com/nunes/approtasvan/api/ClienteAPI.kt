package com.nunes.approtasvan.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ClienteAPI {

    companion object{
        private lateinit var INSTANCE_RETROFIT:Retrofit
        private val url = "https://rotasvanapi.fly.dev/api/"

        private fun getRetrofitINSTANCE():Retrofit{

            if (!::INSTANCE_RETROFIT.isInitialized){
                val http = OkHttpClient.Builder()

                INSTANCE_RETROFIT = Retrofit.Builder()
                    .baseUrl(url)
                    .client(http.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return INSTANCE_RETROFIT
        }

        fun createUsersEndPoint():UserAPI{
            return getRetrofitINSTANCE().create(UserAPI::class.java)
        }
    }

}