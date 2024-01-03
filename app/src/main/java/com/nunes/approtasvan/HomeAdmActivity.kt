package com.nunes.approtasvan

import android.Manifest
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

        requestPermissions(arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.INTERNET), 0)

        registrarEventos()
    }

    private fun registrarEventos() {
        binding.rotaDiaBtn.setOnClickListener(this)
        binding.addAlunoBtn.setOnClickListener(this)
        binding.listarAlunosBtn.setOnClickListener(this)
        binding.sairBtn.setOnClickListener(this)
    }

    override fun onClick(botao: View) {
        when(botao.id){
            binding.rotaDiaBtn.id -> irParaRotaDia()
            binding.addAlunoBtn.id -> irParaCadastrarAluno()
            binding.listarAlunosBtn.id -> irParaListaAlunos()
            binding.sairBtn.id -> {finish()}
        }
    }

    private fun irParaRotaDia() {
        val trancisaoRotasmapa = Intent(baseContext, RotasMapaActivity::class.java)
        startActivity(trancisaoRotasmapa)
    }

    private fun irParaCadastrarAluno() {
        val trancisaoAddAluno = Intent(baseContext, CadastrarAlunoActivity::class.java)
        startActivity(trancisaoAddAluno)
    }

    private fun irParaListaAlunos() {
        val transicaoListaAlunos = Intent(baseContext, ListaAlunosActivity::class.java)
        startActivity(transicaoListaAlunos)
    }
}