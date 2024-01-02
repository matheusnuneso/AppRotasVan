package com.nunes.approtasvan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nunes.approtasvan.databinding.ActivityListaAlunosBinding
import com.nunes.approtasvan.model.User
import com.nunes.approtasvan.view.UserListaAdapter
import com.nunes.approtasvan.viewmodel.ListaUsersViewModel

class ListaAlunosActivity : AppCompatActivity() {

    lateinit var binding: ActivityListaAlunosBinding
    lateinit var adapter: UserListaAdapter
    lateinit var viewModel: ListaUsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListaAlunosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ListaUsersViewModel::class.java]

        configuraRecycleView()
        registrarObserver()
    }

    private fun configuraRecycleView() {

        val user = User(5, "Samuel", "José", 12, "rochedo", "ALUNO", "samuel@gmail.com", -20.654956, -43.781323)
        var listaUser: MutableList<User> = arrayListOf()
        listaUser.add(user)

        viewModel.carregaUsuario(listaUser)
        adapter = UserListaAdapter(viewModel.usuariosLista(), viewModel.deletarUser())

        binding.usuarioList.layoutManager = LinearLayoutManager(baseContext)
        binding.usuarioList.adapter = adapter

    }

    private fun registrarObserver(){
        viewModel.deletarUser().observe(this) {
            viewModel.excluirUser(it)
            adapter.notifyDataSetChanged()
        }
    }


}