package com.nsolution.nfooddriver.Ui.Base

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import com.mapbox.api.directions.v5.models.DirectionsResponse
import com.mapbox.api.directions.v5.models.DirectionsRoute
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.IconFactory
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute
import com.mapbox.services.android.navigation.v5.navigation.MapboxNavigation
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Utils.MarkerMapView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("Registered")
open class BaseMapboxActivity : BaseActivity() {

  private var mapView: MapView? = null
  var mapboxMap: MapboxMap? = null

  private var currentRoute: DirectionsRoute? = null
  private var navigationMapRoute: NavigationMapRoute? = null


  fun getInstanceMapbox() {
    val accessToken = getAccessTokenMapbox()
    Mapbox.getInstance(this, accessToken)
  }

  fun getAccessTokenMapbox(): String {
    return getString(R.string.mapbox_access_token)
  }

  fun getMapView(mapView: MapView?) {
    this.mapView = mapView
  }

  fun getMapboxMap(mapboxMap: MapboxMap) {
    this.mapboxMap = mapboxMap
  }

  fun moveCamera(location: Location?) {
    if (location != null && mapboxMap != null) {
      val latLng = LatLng(location)
      val position = CameraPosition
        .Builder()
        .target(latLng)
        .zoom(14.0)
        .build()
      mapboxMap?.animateCamera(CameraUpdateFactory.newCameraPosition(position), 1000)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    getInstanceMapbox()
  }

  override fun onResume() {
    super.onResume()
    mapView?.onResume()
  }

  override fun onStart() {
    super.onStart()
    mapView?.onStart()
  }

  override fun onStop() {
    super.onStop()
    mapView?.onStop()
  }

  public override fun onPause() {
    super.onPause()
    mapView?.onPause()
  }

  override fun onLowMemory() {
    super.onLowMemory()
    mapView?.onLowMemory()
  }

  override fun onDestroy() {
    super.onDestroy()
    mapView?.onDestroy()
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    mapView?.onSaveInstanceState(outState)
  }

  fun getRoute(origin: Point, destination: Point) {
    val accessTokenMapbox = getAccessTokenMapbox()

    NavigationRoute.builder(this)
      .accessToken(accessTokenMapbox)
      .origin(origin)
      .destination(destination)
      .build()
      .getRoute(object : Callback<DirectionsResponse> {
        override fun onResponse(
          call: Call<DirectionsResponse>,
          response: Response<DirectionsResponse>
        ) {

          val responseBody = response.body()
          val routes = responseBody?.routes()

          if (response.isSuccessful && responseBody != null && routes?.isNotEmpty()!!) {
            currentRoute = routes.first()

            if (navigationMapRoute != null)
              navigationMapRoute?.removeRoute()
            else navigationMapRoute =
              NavigationMapRoute(null, mapView!!, mapboxMap!!, R.style.NavigationViewLight)
            navigationMapRoute?.addRoute(currentRoute)
          }
        }

        override fun onFailure(call: Call<DirectionsResponse>, t: Throwable) {
          t.printStackTrace()
        }
      })
  }

  fun startNavigation() {
    if (currentRoute != null) {
      val simulateRoute = false
      val navigationOptions = NavigationLauncherOptions.builder()
        .directionsRoute(currentRoute)
        .shouldSimulateRoute(simulateRoute)
        .build()

      NavigationLauncher.startNavigation(this, navigationOptions)
    }
  }

  fun addMarker(location: Location?, drawable: Int) {
    if (location != null) {
      val latLng = LatLng(location)
      val markerBitmap = MarkerMapView(this).getIconMarker(drawable)
      val iconFactory = IconFactory.getInstance(this)
      val icon = iconFactory.fromBitmap(markerBitmap)
      val markerOption = MarkerOptions().position(latLng).icon(icon)
      mapboxMap?.addMarker(markerOption)
    }
  }
}