package com.nunes.approtasvan.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nunes.approtasvan.model.Posicao

class RotasActivityViewModel:ViewModel() {

    private val posicaoViewmMoel: MutableLiveData<Posicao> = MutableLiveData()

    fun getPosicaoViewModel(): MutableLiveData<Posicao>{
        return posicaoViewmMoel
    }

    fun setPosicao(latitude:Double, longitude:Double){
        posicaoViewmMoel.value = Posicao(latitude, longitude)
    }

}