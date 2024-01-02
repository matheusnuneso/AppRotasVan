package com.nunes.approtasvan.view

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.nunes.approtasvan.databinding.AlunoItemBinding
import com.nunes.approtasvan.model.User

class UserHold(var binding: AlunoItemBinding, var userDel:MutableLiveData<User>): RecyclerView.ViewHolder(binding.root){

    fun bind(user: User){
        binding.nomeTxt.text = user.nome
        binding.ruaNumTxt.text = "${user.ruaEndereco}, ${user.numEndereco}"
        binding.bairroTxt.text = user.bairroEndereco

        binding.excluirBtn.setOnClickListener {
            userDel.value = user
        }
    }

}