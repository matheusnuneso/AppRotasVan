package com.nunes.approtasvan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.nunes.approtasvan.databinding.ActivityHomeAdmBinding

class HomeAdmActivity : AppCompatActivity(), OnClickListener {

    lateinit var binding: ActivityHomeAdmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeAdmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registrarEventos()
    }

    private fun registrarEventos() {
        binding.rotaDiaBtn.setOnClickListener(this)
    }

    override fun onClick(botao: View) {
        when(botao.id){
            binding.rotaDiaBtn.id -> irParaRotaDia()
        }
    }

    private fun irParaRotaDia() {
        val trancisaoRotasmapa = Intent(baseContext, RotasMapaActivity::class.java)
        startActivity(trancisaoRotasmapa)
    }
}