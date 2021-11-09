package com.nsolution.nfooddriver.Utils

import android.annotation.SuppressLint
import android.content.Context
import com.mapbox.android.core.location.*

class DeviceLocation(val context : Context){

  private val DEFAULT_INTERVAL_IN_MILLISECONDS = 750L
  lateinit var locationEngine: LocationEngine

  @SuppressLint("MissingPermission")
  fun initLocationEngine(callback : LocationEngineCallback<LocationEngineResult>) {

    locationEngine = LocationEngineProvider.getBestLocationEngine(context)

    val request = LocationEngineRequest
      .Builder(DEFAULT_INTERVAL_IN_MILLISECONDS)
      .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
      .setFastestInterval(750)
      .build()

    locationEngine.requestLocationUpdates(request, callback, context.mainLooper)
    requestLocation(callback)
  }

  fun requestLocation(callback : LocationEngineCallback<LocationEngineResult>){
    locationEngine.getLastLocation(callback)
  }

  fun removeRequestLocation(callback : LocationEngineCallback<LocationEngineResult>){
    locationEngine.removeLocationUpdates(callback)
  }
}