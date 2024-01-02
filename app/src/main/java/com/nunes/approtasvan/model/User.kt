package com.nunes.approtasvan.model

import com.google.gson.annotations.SerializedName

class User {
    constructor(
        id: Int,
        nome: String,
        ruaEndereco: String,
        numEndereco: Int,
        bairroEndereco: String,
        role: String,
        email: String,
        latitude: Double,
        longitude: Double
    ) {
        this.id = id
        this.nome = nome
        this.ruaEndereco = ruaEndereco
        this.numEndereco = numEndereco
        this.bairroEndereco = bairroEndereco
        this.role = role
        this.email = email
        this.latitude = latitude
        this.longitude = longitude
    }

    @SerializedName("id")
    var id:Int = 0

    @SerializedName("nome")
    var nome:String = ""

    @SerializedName("ruaEndereco")
    var ruaEndereco:String = ""

    @SerializedName("numEndereco")
    var numEndereco:Int = 0

    @SerializedName("bairroEndereco")
    var bairroEndereco:String = ""

    @SerializedName("role")
    var role:String = ""

    @SerializedName("email")
    var email:String = ""

    @SerializedName("latitude")
    var latitude:Double = 0.0

    @SerializedName("longitude")
    var longitude:Double = 0.0
}