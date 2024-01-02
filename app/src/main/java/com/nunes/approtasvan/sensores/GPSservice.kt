package com.nunes.approtasvan.sensores

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import com.nunes.approtasvan.viewmodel.RotasActivityViewModel

@SuppressLint("MissingPermission")
class GPSservice(var contexto:Context, var viewModel:RotasActivityViewModel): LocationListener {

    private var locationManager: LocationManager

    init {
        locationManager = contexto.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 2f, this)
    }

    override fun onLocationChanged(location: Location) {
        viewModel.setPosicao(location.latitude, location.longitude)
    }

}