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
        binding.sairBtn.setOnClickListener(this)
    }

    override fun onClick(botao: View) {
        when(botao.id){
            binding.naoVaiBtn.id -> salvarPresencaAluno()
            binding.sairBtn.id -> {finish()}
        }
    }

    private fun salvarPresencaAluno(){
        val dia = getDia()
        val mes = getMes()
        val ano = binding.dataTxt.year
        val data = "${dia}/${mes}/${ano}"
        this.idAluno = intent.getIntExtra("id", 0)

        var gerenPresen = GerenPresen(idAluno as Int, data)

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

    private fun getDia():String{
        val dia:Int = binding.dataTxt.dayOfMonth

        if (dia < 10){
            return "0${dia}"
        }
        return dia.toString()
    }

    private fun getMes():String{
        val mes:Int = binding.dataTxt.month + 1

        if (mes < 10){
            return "0${mes}"
        }
        return mes.toString()
    }
}