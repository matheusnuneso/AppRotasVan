package com.nunes.approtasvan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import com.nunes.approtasvan.api.ClienteAPI
import com.nunes.approtasvan.databinding.ActivityHomeAlunoBinding
import com.nunes.approtasvan.model.GerenPresen
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeAlunoActivity : AppCompatActivity(), OnClickListener {

    lateinit var binding: ActivityHomeAlunoBinding

    var idAluno:Int? = null

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
            binding.naoVaiBtn.id -> salvarPresencaAluno()
        }
    }

    private fun salvarPresencaAluno(){
        val dia = binding.dataTxt.dayOfMonth
        val mes = binding.dataTxt.month + 1
        val ano = binding.dataTxt.year
        val data = "${dia}/${mes}/${ano}"
        this.idAluno = intent.getIntExtra("id", 0)

        var gerenPresen = GerenPresen()
        gerenPresen.idAluno = idAluno as Int
        gerenPresen.dataNPresenca = data

        var api = ClienteAPI.createGerenPresenEndPoint()
        val requisicao: Call<GerenPresen> = api.saveGerenPresen(gerenPresen)

        requisicao.enqueue(object :Callback<GerenPresen>{
            override fun onResponse(call: Call<GerenPresen>, response: Response<GerenPresen>) {
                if (response.isSuccessful){
                    Toast.makeText(baseContext, "Salvo com sucesso!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(baseContext, "algo deu errado", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<GerenPresen>, t: Throwable) {
                Toast.makeText(baseContext, "t.message", Toast.LENGTH_LONG).show()
            }

        })
    }
}