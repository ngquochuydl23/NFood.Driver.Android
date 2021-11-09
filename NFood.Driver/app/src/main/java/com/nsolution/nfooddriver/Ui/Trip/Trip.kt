package com.nsolution.nfooddriver.Ui.Trip

import android.app.Activity
import android.content.*
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.maps.MapView
import com.nsolution.nfooddriver.Models.LocationDto
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Service.DriverService
import com.nsolution.nfooddriver.SharedReferences.NavigationAppSharedPreferences
import com.nsolution.nfooddriver.Ui.Base.BaseMapboxActivity
import com.nsolution.nfooddriver.Ui.HomeScreen.HomeScreen
import com.nsolution.nfooddriver.Utils.LocationComponent
import com.nsolution.nfooddriver.Utils.NavigationApp
import kotlinx.android.synthetic.main.activity_trip.*


class Trip : BaseMapboxActivity() {

  private val HAVE_GONE_TO_RESTAURANT_STEP = "HAVE_GONE_TO_RESTAURANT_STEP"
  private val DELIVERY_FOOD_STEP = "DELIVERY_FOOD_STEP"

  private var mapView: MapView? = null
  lateinit var cancelOrderButton: Button
  private var currentLocationDevice: Location? = null
  private var locationComponent: LocationComponent? = null
  private var boundService: Boolean? = null
  private var driverService: DriverService? = null

  companion object {
    lateinit var activity: Activity
    lateinit var restaurantInformationLayout: View
    var currentStep: String? = null
  }

  init {
    activity = this
    currentStep = HAVE_GONE_TO_RESTAURANT_STEP
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_trip)

    locationComponent = LocationComponent(this)

    cancelOrderButton = findViewById(R.id.cancelOrderButton)
    restaurantInformationLayout = findViewById(R.id.restaurantInformationLayout)

    mapView = findViewById(R.id.mapView)
    mapView?.onCreate(savedInstanceState)

    initialView()
  }

  private fun initialView() {
    getMapView(mapView)

    mapView?.getMapAsync { mapboxMap ->
      getMapboxMap(mapboxMap)
      locationComponent?.enableLocationComponent(mapboxMap)
    }

    stepButton.setText(getString(R.string.i_went_to_the_restaurant))
    restaurantInformationLayout.visibility = View.VISIBLE

    stepButton.setOnClickListener {
      completeStepAction()
    }

    navigateMapButton.setOnClickListener {
      val navigationApp = NavigationAppSharedPreferences(this).getData()
      if (navigationApp == NavigationApp.NFOOD_NAVIGATION) {
        navigateMapAction()
      } else {
        val locaton = LocationDto().apply {
          latitude = 11.9528
          longitude = 108.4399
        }
        navigationWithGoogleMap(locaton)
      }
    }

    moveCameraButton.setOnClickListener {
      moveToLocationDevice()
    }

    cancelOrderButton.setOnClickListener {
      cancelOrderAction()
    }
  }

  private fun completeStepAction() {
    when (currentStep) {
      HAVE_GONE_TO_RESTAURANT_STEP -> {
        sendArrivedRestaurantAction()
        navigateTo(TakeOrder::class.java)
      }
      DELIVERY_FOOD_STEP -> {
        navigateTo(DeliveryForCustomer::class.java)
      }
    }
  }

  private fun navigateMapAction() {
    startNavigation()
  }

  private fun moveToLocationDevice() {
    moveCamera(currentLocationDevice)
  }

  override fun onRestart() {
    super.onRestart()
    if (currentStep == DELIVERY_FOOD_STEP) {
      stepButton.setText(getString(R.string.i_arrived))
    }
  }

  private fun cancelOrderAction() {
    navigateTo(HomeScreen::class.java)
    finish()
  }

  private val receiver = object : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
      val bundle = intent?.extras
      if (bundle != null) {
        val newLocation = bundle.getParcelable<Location>(DriverService.NEW_LOCATION)
        if (currentLocationDevice == null) {
          moveCamera(newLocation)
        }
        currentLocationDevice = newLocation
      }
    }
  }

  override fun onResume() {
    super.onResume()
    registerReceiver(receiver, IntentFilter(DriverService.NEW_LOCATION))
  }

  private val connectionService = object : ServiceConnection {
    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
      val binder = service as DriverService.DriverBinder
      boundService = true
      driverService = binder.service
      sendIsComingToRestaurantAction()
    }

    override fun onServiceDisconnected(name: ComponentName?) {
      boundService = false
    }
  }

  private fun blindService() {
    Intent(this, DriverService::class.java).also { intent ->
      bindService(intent, connectionService, Context.BIND_AUTO_CREATE)
    }
  }

  private fun unblindService() {
    unbindService(connectionService)
  }

  override fun onStart() {
    super.onStart()
    blindService()
  }

  override fun onStop() {
    super.onStop()
    unblindService()
    unregisterReceiver(receiver)
  }

  private fun sendIsComingToRestaurantAction() {
    driverService?.sendIsComingToRestaurantAction()
  }

  private fun sendArrivedRestaurantAction() {
    driverService?.sendArrivedRestaurantAction()
  }

  private fun sendConfirmTakeOrderAction() {
    driverService?.sendConfirmTakeOrderAction()
  }

  private fun navigationWithGoogleMap(location: LocationDto) {
    val gmmIntentUri =
      Uri.parse("google.navigation:q=${location.latitude},${location.longitude}")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    startActivity(mapIntent)
  }
}
