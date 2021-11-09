package com.nsolution.nfooddriver.Utils

import android.content.Context
import androidx.core.content.ContextCompat.getColor
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponent
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.LocationComponentOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.maps.UiSettings
import com.nsolution.nfooddriver.R


class LocationComponent(val context: Context) {

  private val layerId = "LAYER_ID"
  private var locationComponent: LocationComponent? = null

  fun enableLocationComponent(mapboxMap : MapboxMap?) {
    if(mapboxMap != null){

      locationComponent = mapboxMap.getLocationComponent()

      mapboxMap.setStyle(Style.MAPBOX_STREETS) {
        val uiSettings: UiSettings = mapboxMap.uiSettings
        uiSettings.isCompassEnabled = false
        uiSettings.isLogoEnabled = false
        uiSettings.isAttributionEnabled = false

        val locationComponentOptions = LocationComponentOptions.builder(context)
          .layerBelow(layerId)
          .foregroundDrawable(R.drawable.map_marker_dark)
          .bearingTintColor(getColor(context, R.color.green))
          .accuracyAlpha(1F)
          .build()

        val locationComponentActivationOptions = LocationComponentActivationOptions
          .builder(context, it)
          .locationComponentOptions(locationComponentOptions)
          .build()

        locationComponent?.activateLocationComponent(locationComponentActivationOptions)
        locationComponent?.isLocationComponentEnabled = true
        locationComponent?.cameraMode = CameraMode.TRACKING
        locationComponent?.renderMode = RenderMode.GPS

      }
    }
  }
}