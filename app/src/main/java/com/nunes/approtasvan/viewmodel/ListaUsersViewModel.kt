package com.nunes.approtasvan.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nunes.approtasvan.model.User

class ListaUsersViewModel: ViewModel() {

    private var users: MutableLiveData<MutableList<User>> = MutableLiveData()
    private var deletarUser:MutableLiveData<User> = MutableLiveData()

    fun carregaUsuario(listaUsers: MutableList<User>){
        users.value = listaUsers
    }

    fun usuariosLista(): MutableList<User>? {
        return users.value
    }

    fun deletarUser(): MutableLiveData<User>{
        return deletarUser
    }

    fun excluirUser(usuario: User) {
        users.value?.remove(usuario)
    }

}