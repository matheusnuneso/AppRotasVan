package com.nunes.approtasvan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import com.nunes.approtasvan.api.ClienteAPI
import com.nunes.approtasvan.databinding.ActivityMainBinding
import com.nunes.approtasvan.model.AuthUser
import com.nunes.approtasvan.model.User
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
                    val nome = response.body().let { it!!.nome }
                    Toast.makeText(baseContext, nome, Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(baseContext, "Errado", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(baseContext, "t.message", Toast.LENGTH_LONG).show()
            }

        })

    }
}