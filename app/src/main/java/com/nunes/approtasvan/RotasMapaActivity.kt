package com.nunes.approtasvan

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nunes.approtasvan.api.ClienteAPI
import com.nunes.approtasvan.databinding.ActivityRotasMapaBinding
import com.nunes.approtasvan.model.Posicao
import com.nunes.approtasvan.model.User
import com.nunes.approtasvan.sensores.GPSservice
import com.nunes.approtasvan.viewmodel.RotasActivityViewModel
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RotasMapaActivity : AppCompatActivity(), OnClickListener {

    lateinit var binding: ActivityRotasMapaBinding
    lateinit var gpsService: GPSservice
    lateinit var viewModel: RotasActivityViewModel

    lateinit var mapa: MapView
    lateinit var controleMapa: IMapController
    lateinit var meuLayer: MyLocationNewOverlay


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRotasMapaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestPermissions(arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.INTERNET), 0)

        viewModel = ViewModelProvider(this)[RotasActivityViewModel::class.java]
        gpsService = GPSservice(baseContext, viewModel)

        registrarObservers()

        configuraMapa()

        registrarEventos()
    }

    private fun registrarEventos() {
        binding.voltarBtn.setOnClickListener(this)
    }

    override fun onClick(botao: View) {
        when(botao.id){
            binding.voltarBtn.id -> {finish()}
        }
    }

    private fun registrarObservers() {
        viewModel.getPosicaoViewModel().observe(this, object : Observer<Posicao> {
            override fun onChanged(value: Posicao) {
                controleMapa.animateTo(GeoPoint(value.latitude, value.longitude))
            }

        })
    }

    private fun configuraMapa() {
        Configuration.getInstance().userAgentValue="matheusnuneso"

        mapa = binding.osmMapview
        mapa.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE)
        mapa.mapCenter
        mapa.setMultiTouchControls(true)

        controleMapa = mapa.controller
        controleMapa.setZoom(17.0)

        meuLayer = MyLocationNewOverlay(GpsMyLocationProvider(this), mapa)
        meuLayer.enableFollowLocation()
        meuLayer.enableMyLocation()
        meuLayer.setPersonAnchor(10f,10f)
        mapa.overlays.add(meuLayer)

        requisitarAlunosPresentes()
    }

    private fun requisitarAlunosPresentes() {

        var api = ClienteAPI.createGerenPresenEndPoint()
        val requisicao: Call<List<User>> = api.getAlunosPresnetes("02-01-2024")

        requisicao.enqueue(object: Callback<List<User>>{

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.body() != null){
                    addMarcadoresAlunos(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(baseContext, "algo deu errado", Toast.LENGTH_LONG).show()
            }

        })

    }

    private fun addMarcadoresAlunos(listaUser: List<User>){
        for (user in listaUser){
            addMarcador(Posicao(user.latitude, user.longitude))
        }
    }

    private fun addMarcador(posicao: Posicao){
        val m = Marker(mapa)
        m.position = GeoPoint(posicao.latitude, posicao.longitude)
        mapa.overlays.add(m)
    }

}