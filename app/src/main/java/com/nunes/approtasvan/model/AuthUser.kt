package com.nunes.approtasvan.model

import com.google.gson.annotations.SerializedName

class AuthUser() {
    @SerializedName("email")
    var email:String = ""

    @SerializedName("senha")
    var senha:String = ""
}