package com.nunes.approtasvan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import com.nunes.approtasvan.databinding.ActivityMainBinding

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
        Toast.makeText(baseContext, "deu certo", Toast.LENGTH_LONG).show()
    }
}