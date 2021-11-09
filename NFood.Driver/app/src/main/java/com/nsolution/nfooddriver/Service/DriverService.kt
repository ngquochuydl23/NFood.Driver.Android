package com.nsolution.nfooddriver.Service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.location.Location
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_MIN
import com.mapbox.android.core.location.LocationEngineCallback
import com.mapbox.android.core.location.LocationEngineResult
import com.nsolution.nfooddriver.DriverContext
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.IncomingTrip.IncomingTrip
import com.nsolution.nfooddriver.Utils.DeviceLocation


class DriverService : Service(), DriverContext.AddListenDriver,
  LocationEngineCallback<LocationEngineResult> {

  private val CHANNEL_ID = "exampleServiceChannel"
  private val CHANNEL_NAME = "exampleServiceChannel"
  private val REQUEST_FOREGROUND = 101

  private val DRIVER_IS_OFFLINE = false
  private val DRIVER_IS_ONLINE = true

  private var driverContext: DriverContext? = null
  private var deviceLocation: DeviceLocation? = null
  private var mBinder: IBinder = DriverBinder()
  private var isDisconnectFromUser: Boolean? = null

  companion object {
    val IS_ERROR = "IS_ERROR"
    val NEW_LOCATION = "NEW_LOCATION"
    val DRIVER_STATUS = "DRIVER_STATUS"
  }

  override fun onCreate() {
    super.onCreate()
    driverContext = DriverContext(this)
    deviceLocation = DeviceLocation(this)

    driverContext?.hubBuilder("")
    driverContext?.addListenerDriver(this)
    deviceLocation?.initLocationEngine(this)
  }


  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

    return START_STICKY
  }

  private fun createNotificationChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val serviceChannel =
        NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
      val manager = getSystemService(NotificationManager::class.java)
      manager.createNotificationChannel(serviceChannel)
    }
  }

  override fun onBind(intent: Intent?): IBinder? {
    stopForeground(true)
    return mBinder
  }

  override fun onRebind(intent: Intent) {
    stopForeground(true)
    super.onRebind(intent)
  }

  override fun onUnbind(intent: Intent): Boolean {
    startForegroundService()
    return true
  }

  override fun onComingTrip() {
    deviceLocation?.removeRequestLocation(this)
    Intent(this, IncomingTrip::class.java).let {
      it.flags = FLAG_ACTIVITY_NEW_TASK
      startActivity(it)
    }
  }

  override fun onCancelTrip() {
    stopForeground(true)
  }

  override fun onDisconnect() {
    isDisconnectFromUser?.let {
      if (!it) {
        goOnline()
      } else sendDriverStatusBoardcast(DRIVER_IS_OFFLINE)
    }
  }

  @SuppressLint("CheckResult")
  fun goOnline() {
    driverContext?.connectToHub()?.subscribe({
      isDisconnectFromUser = false
      sendDriverStatusBoardcast(DRIVER_IS_ONLINE)
    }, {
      sendDriverStatusBoardcast(DRIVER_IS_OFFLINE)
      sendErrorBoardcast(true)
      it.printStackTrace()
    })
  }

  @SuppressLint("CheckResult")
  fun goOffline() {
    isDisconnectFromUser = true
    driverContext?.disconnectFromHub()?.subscribe({
      sendDriverStatusBoardcast(DRIVER_IS_OFFLINE)
    }, {
      it.printStackTrace()
    })
  }

  fun sendAcceptTripAction() {
    stopForeground(true)
    driverContext?.sendAcceptTripAction()
  }

  fun sendDeclineTripAction() {
    stopForeground(true)
    driverContext?.sendDeclineTripAction()
  }

  fun sendIsComingToRestaurantAction() {
    driverContext?.sendIsComingToRestaurantAction()
    stopForeground(true)
  }

  fun sendArrivedRestaurantAction() {
    driverContext?.sendArrivedRestaurantAction()
  }

  fun sendConfirmTakeOrderAction() {
    driverContext?.sendConfirmTakeOrderAction()
  }

  fun sendOrderBillAction() {
    driverContext?.sendOrderBillAction()
  }

  fun sendArrivedCustomerAction() {
    driverContext?.sendArrivedCustomerAction()
  }

  fun sendConfirmDeliveryAction() {
    driverContext?.sendConfirmDeliveryAction()
  }

  val isOnline: Boolean
    get() = driverContext?.isConnectHub() as Boolean


  inner class DriverBinder : Binder() {
    val service: DriverService
      get() = this@DriverService
  }

  override fun onSuccess(result: LocationEngineResult?) {
    onNewLocation(result?.lastLocation)
  }

  override fun onFailure(exception: Exception) {
    exception.printStackTrace()
  }

  @SuppressLint("CheckResult")
  private fun onNewLocation(location: Location?){
    if (isOnline && location != null) {
      driverContext?.updateDeviceLocation(location)?.doOnError {
        it.printStackTrace()
      }
    }
    val intent = Intent(NEW_LOCATION)
    intent.putExtra(NEW_LOCATION, location)
    sendBroadcast(intent)
  }


  private fun sendDriverStatusBoardcast(driverStatus: Boolean) {
    val intent = Intent(DRIVER_STATUS)
    intent.putExtra(DRIVER_STATUS, driverStatus)
    sendBroadcast(intent)
  }

  private fun sendErrorBoardcast(isError : Boolean) {
    val intent = Intent(DRIVER_STATUS)
    intent.putExtra(IS_ERROR, isError)
    sendBroadcast(intent)
  }

  private fun startForegroundService() {
    if (isOnline) {
      createNotificationChannel()

      val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
      val notification = notificationBuilder.setOngoing(true)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle(getString(R.string.content_title))
        .setContentText(getString(R.string.content_text))
        .setPriority(PRIORITY_MIN)
        .setCategory(Notification.CATEGORY_SERVICE)
        .build()

      startForeground(REQUEST_FOREGROUND, notification)
    }
  }
}