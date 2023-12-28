package com.nunes.approtasvan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.nunes.approtasvan.databinding.ActivityHomeAlunoBinding

class HomeAlunoActivity : AppCompatActivity(), OnClickListener {

    lateinit var binding: ActivityHomeAlunoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeAlunoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registrarEventos()
    }

    private fun registrarEventos() {
        binding.naoVaiBtn.setOnClickListener(this)
    }

    override fun onClick(botao: View) {
        when(botao.id){

        }
    }
}