package com.nunes.approtasvan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nunes.approtasvan.databinding.ActivityListaAlunosBinding

class ListaAlunosActivity : AppCompatActivity() {

    lateinit var binding: ActivityListaAlunosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListaAlunosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registrarEventos()
    }

    private fun registrarEventos() {

    }
}