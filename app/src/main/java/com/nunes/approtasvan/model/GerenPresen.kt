package com.nunes.approtasvan.model

import com.google.gson.annotations.SerializedName

class GerenPresen(
    @SerializedName("idAluno") var idAluno: Int = 0,
    @SerializedName("dataNPresenca") var dataNPresenca: String = ""
)
