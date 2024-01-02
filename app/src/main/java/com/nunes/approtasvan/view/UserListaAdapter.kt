package com.nunes.approtasvan.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.nunes.approtasvan.databinding.AlunoItemBinding
import com.nunes.approtasvan.model.User

class UserListaAdapter(val users: MutableList<User>?, var usuarioDel:MutableLiveData<User>):
    RecyclerView.Adapter<UserHold>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHold {
        val item = AlunoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return UserHold(item, usuarioDel)
    }

    override fun getItemCount(): Int {
        return if(users != null){users.size }else{0}
    }

    override fun onBindViewHolder(holder: UserHold, position: Int) {
        holder.bind(users!![position])
    }


}