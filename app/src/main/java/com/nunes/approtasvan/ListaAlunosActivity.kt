package com.nunes.approtasvan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nunes.approtasvan.api.ClienteAPI
import com.nunes.approtasvan.databinding.ActivityListaAlunosBinding
import com.nunes.approtasvan.model.User
import com.nunes.approtasvan.view.UserListaAdapter
import com.nunes.approtasvan.viewmodel.ListaUsersViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        var api = ClienteAPI.createUsersEndPoint()
        val requisicao: Call<List<User>> = api.getAlunos()

        requisicao.enqueue(object: Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                preencheItens(response.body() as MutableList<User>)
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun preencheItens(listaUser: MutableList<User>) {

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