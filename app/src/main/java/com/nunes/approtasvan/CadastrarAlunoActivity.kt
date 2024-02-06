package com.nunes.approtasvan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import com.nunes.approtasvan.api.ClienteAPI
import com.nunes.approtasvan.databinding.ActivityCadastrarAlunoBinding
import com.nunes.approtasvan.model.User
import com.nunes.approtasvan.model.roleUserENUM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastrarAlunoActivity : AppCompatActivity(), OnClickListener {

    lateinit var binding: ActivityCadastrarAlunoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastrarAlunoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registrarEventos()
    }

    private fun registrarEventos() {
        binding.cadastrarBtn.setOnClickListener(this)
    }

    override fun onClick(botao: View) {
        when(botao.id){
            binding.cadastrarBtn.id -> cadastrarAluno()
        }
    }

    private fun cadastrarAluno() {
        var user = User()
        user.nome = binding.nomeTxt.text.toString()
        user.ruaEndereco = binding.ruaTxt.text.toString()
        user.numEndereco = binding.numTxt.text.toString().toInt()
        user.bairroEndereco = binding.bairroTxt.text.toString()
        user.role = roleUserENUM.ALUNO.toString()
        user.email = binding.emailTxt.text.toString()
        user.latitude = binding.latitudeTxt.text.toString().toDouble()
        user.longitude = binding.longitudeTxt.text.toString().toDouble()
        user.idVan = intent.getIntExtra("idVan", 0)

        var api = ClienteAPI.createUsersEndPoint()
        val requisicao: Call<User> = api.saveUser(user)

        requisicao.enqueue(object :Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    Toast.makeText(baseContext, "Aluno salvo com sucesso!", Toast.LENGTH_LONG).show()

                    finish()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(baseContext, "Algo deu errado", Toast.LENGTH_LONG).show()
            }

        })

    }
}