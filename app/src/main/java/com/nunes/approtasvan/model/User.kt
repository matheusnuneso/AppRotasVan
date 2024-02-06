package com.nunes.approtasvan.model

import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("nome") var nome: String = "",
    @SerializedName("ruaEndereco") var ruaEndereco: String = "",
    @SerializedName("numEndereco") var numEndereco: Int = 0,
    @SerializedName("bairroEndereco") var bairroEndereco: String = "",
    @SerializedName("role") var role: String = "",
    @SerializedName("email") var email: String = "",
    @SerializedName("latitude") var latitude: Double = 0.0,
    @SerializedName("longitude") var longitude: Double = 0.0,
    @SerializedName("idVan") var idVan: Int = 0
)