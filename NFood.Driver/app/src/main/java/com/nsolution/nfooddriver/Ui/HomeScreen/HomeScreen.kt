package com.nsolution.nfooddriver.Ui.HomeScreen

import android.content.*
import android.location.Location
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.maps.MapView
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Receivers.BootReceiver
import com.nsolution.nfooddriver.Service.DriverService
import com.nsolution.nfooddriver.SharedReferences.AutoReceiveTripSharedPreferences
import com.nsolution.nfooddriver.Ui.Base.BaseMapboxActivity
import com.nsolution.nfooddriver.Ui.BottomSheet.DriverChecklistBottomSheet
import com.nsolution.nfooddriver.Ui.BottomSheet.NoInternetBottomSheet
import com.nsolution.nfooddriver.Ui.Dialog.DialogConnectHubError
import com.nsolution.nfooddriver.Ui.Earnings.Earnings
import com.nsolution.nfooddriver.Ui.Trip.TakePhotoOrder
import com.nsolution.nfooddriver.Utils.LocationComponent
import kotlinx.android.synthetic.main.activity_home_screen.*
import net.cachapa.expandablelayout.ExpandableLayout


class HomeScreen : BaseMapboxActivity() {

  private var mapView: MapView? = null

  lateinit var bottomSheetNoInternet: NoInternetBottomSheet
  lateinit var bottomSheetDriverChecklist: DriverChecklistBottomSheet

  lateinit var expandedLayoutEarning: ExpandableLayout
  lateinit var expandedLayoutStatus: ExpandableLayout
  lateinit var turnOnOffButton: Button
  lateinit var seeMyEarnings: View
  lateinit var statusTitle: TextView
  lateinit var statusSubtitle: TextView
  lateinit var dialogConnectError: DialogConnectHubError
  lateinit var autoReceiveTripSwitch : SwitchCompat

  private var currentLocationDevice: Location? = null
  private var boundService: Boolean? = null
  private var driverService: DriverService? = null
  private var locationComponent: LocationComponent? = null
  private var autoReceiveTripSharedPreferences : AutoReceiveTripSharedPreferences? = null

  private var isShowDriverCheckList = false


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home_screen)

    initNotificationService()
    registerBootBroadCastReceiver()

    autoReceiveTripSharedPreferences = AutoReceiveTripSharedPreferences(this)
    locationComponent = LocationComponent(this)

    dialogConnectError = DialogConnectHubError(this)
    dialogConnectError.onBlindDialog()
    dialogConnectError.addListener(dialogHubErrorListener)

    bottomSheetNoInternet = NoInternetBottomSheet()
    bottomSheetDriverChecklist = DriverChecklistBottomSheet()

    turnOnOffButton = findViewById(R.id.turnOnOffButton)
    expandedLayoutEarning = findViewById(R.id.expandedLayoutEarning)
    expandedLayoutStatus = findViewById(R.id.expandedLayoutStatus)
    seeMyEarnings = findViewById(R.id.seeMyEarnings)
    statusTitle = findViewById(R.id.statusTitle)
    statusSubtitle = findViewById(R.id.statusSubtitle)
    autoReceiveTripSwitch = findViewById(R.id.autoReceiveTripSwitch)

    mapView = findViewById(R.id.mapView)
    mapView?.onCreate(savedInstanceState)

    initialView()
    checkDriverService()
  }

  private fun initialView() {
    getMapView(mapView)
    autoReceiveTripSwitch.isChecked = autoReceiveTripSharedPreferences?.getData() as Boolean

    if (!isNetworkConnected())
      showBottomSheet(bottomSheetNoInternet)

    mapView?.getMapAsync { mapboxMap ->
      getMapboxMap(mapboxMap)
      locationComponent?.enableLocationComponent(mapboxMap)
    }

    moreButton.setOnClickListener {
      navigateTo(More::class.java)
    }

    earningsButton.setOnClickListener {
      if (expandedLayoutEarning.isExpanded)
        expandedLayoutEarning.collapse()
      else expandedLayoutEarning.expand()
    }

    statusButton.setOnClickListener {
      if (expandedLayoutStatus.isExpanded)
        expandedLayoutStatus.collapse()
      else expandedLayoutStatus.expand()
    }

    turnOnOffButton.setOnClickListener {
      expandedLayoutStatus.collapse()

      if (!isNetworkConnected()) {
        showBottomSheet(bottomSheetNoInternet)
        return@setOnClickListener
      }

      if (driverService?.isOnline as Boolean)
        driverService?.goOffline()
      else {
        dialogConnectError.dismiss()
        driverService?.goOnline()
      }
    }

    moveCameraButton.setOnClickListener {
      moveToLocationDevice()
    }

    seeMyEarnings.setOnClickListener {
      navigateTo(Earnings::class.java)
    }

    autoReceiveTripSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
     autoReceiveTripSharedPreferences?.setData(isChecked)
    }
  }

  private fun checkDriverService() {
    if (!isServiceRunning(DriverService::class.java)) {
      Intent(this, DriverService::class.java).also { intent ->
        startService(intent)
      }
    }
  }

  private fun initNotificationService() {

  }

  private fun onlineAction() {
    if (!isShowDriverCheckList) {
      isShowDriverCheckList = true
      showBottomSheet(bottomSheetDriverChecklist)
    }
    statusButton.setColorFilter(resources.getColor(R.color.green))
    turnOnOffButton.backgroundTintList = resources.getColorStateList(R.color.red)

    statusTitle.text = getString(R.string.status_title_online)
    statusSubtitle.text = getString(R.string.status_subtitle_online)
    turnOnOffButton.text = getString(R.string.turn_off)
  }

  private fun offlineAction() {
    statusButton.setColorFilter(resources.getColor(R.color.red))
    turnOnOffButton.backgroundTintList = resources.getColorStateList(R.color.green)

    statusTitle.text = getString(R.string.status_title_offline)
    statusSubtitle.text = getString(R.string.status_subtitle_offline)
    turnOnOffButton.text = getString(R.string.turn_on)
  }

  private fun blindService() {
    Intent(this, DriverService::class.java).also { intent ->
      bindService(intent, connectionService, Context.BIND_AUTO_CREATE)
    }
  }

  private fun unblindService() {
    unbindService(connectionService)
  }

  private val connectionService = object : ServiceConnection {
    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
      val binder = service as DriverService.DriverBinder
      boundService = true
      driverService = binder.service
      setViewDriverStatus(driverService?.isOnline as Boolean)
    }

    override fun onServiceDisconnected(name: ComponentName?) {
      boundService = false
    }
  }

  override fun onStart() {
    super.onStart()
    blindService()
  }

  override fun onStop() {
    super.onStop()
    unblindService()
    unregisterReceiver(receiverLocation)
    unregisterReceiver(receiverDriverStatus)
  }

  private val receiverLocation = object : BroadcastReceiver() {
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

  private val receiverDriverStatus = object : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
      val bundle = intent?.extras
      if (bundle != null) {

        val driverStatus = bundle.getBoolean(DriverService.DRIVER_STATUS)
        setViewDriverStatus(driverStatus)

        val isError = bundle.getBoolean(DriverService.IS_ERROR)
        if (isError) {
          dialogConnectError.showDialog()
        }
      }
    }
  }

  private val receiverDriverEarning = object : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
      val bundle = intent?.extras
      if (bundle != null) {

      }
    }
  }

  override fun onResume() {
    super.onResume()
    registerReceiver(receiverLocation, IntentFilter(DriverService.NEW_LOCATION))
    registerReceiver(receiverDriverStatus, IntentFilter(DriverService.DRIVER_STATUS))
  }

  private fun registerBootBroadCastReceiver() {
    val bootBroadcastReceiver = BootReceiver()
    val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
    registerReceiver(bootBroadcastReceiver, intentFilter)
  }

  private fun setViewDriverStatus(isOnline: Boolean) {
    if (isOnline) {
      onlineAction()
    } else offlineAction()
  }

  private fun moveToLocationDevice() {
    moveCamera(currentLocationDevice)
  }

  private val dialogHubErrorListener = object : DialogConnectHubError.DialogConnectHubListener {
    override fun tryAgainAction() {
      driverService?.goOnline()
      dialogConnectError.dismiss()
    }
  }
}
