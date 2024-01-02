package com.nunes.approtasvan

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import com.nunes.approtasvan.api.ClienteAPI
import com.nunes.approtasvan.databinding.ActivityMainBinding
import com.nunes.approtasvan.model.AuthUser
import com.nunes.approtasvan.model.User
import com.nunes.approtasvan.model.roleUserENUM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), OnClickListener {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registrarEventos()
    }

    private fun registrarEventos() {
        binding.loginBtn.setOnClickListener(this)
    }

    override fun onClick(botao: View) {
        when(botao.id){
            binding.loginBtn.id -> realizarLogin()
        }
    }

    private fun realizarLogin() {
        val email:String = binding.userNameTxt.text.toString()
        val senha:String = binding.senhaTxt.text.toString()

        val authUser = AuthUser()
        authUser.email = email
        authUser.senha = senha

        var api = ClienteAPI.createUsersEndPoint()
        val requisicao: Call<User> = api.authUser(authUser)

        requisicao.enqueue(object :Callback<User>{

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    irParaHome(response.body())
                } else {
                    Toast.makeText(baseContext, "Usuário ou senha incorretos", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(baseContext, "t.message", Toast.LENGTH_LONG).show()
            }

        })

        //irParaHome(User(5, "Samuel", "José", 12, "rochedo", "ADM", "samuel@gmail.com", -20.654956, -43.781323))

    }

    private fun irParaHome(body: User?) {

        if (body != null){

            when(body.role){
                roleUserENUM.ALUNO.toString() -> irParaHomeAluno(body.id)
                roleUserENUM.MOTORISTA.toString() -> irParaHomeAdm(body.id)
                roleUserENUM.ADM.toString() -> irParaHomeAdm(body.id)
            }

        }

    }

    private fun irParaHomeAdm(id: Int) {
        val trancisaoHomeAdm = Intent(baseContext, HomeAdmActivity::class.java)
        trancisaoHomeAdm.putExtra("id", id)
        startActivity(trancisaoHomeAdm)
    }

    private fun irParaHomeAluno(id: Int) {
        val trancisaoHomeAluno = Intent(baseContext, HomeAlunoActivity::class.java)
        trancisaoHomeAluno.putExtra("id", id)
        startActivity(trancisaoHomeAluno)
    }
}