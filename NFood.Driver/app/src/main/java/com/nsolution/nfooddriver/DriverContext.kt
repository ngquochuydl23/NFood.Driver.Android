package com.nsolution.nfooddriver

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import com.microsoft.signalr.HubConnectionState
import com.nsolution.nfooddriver.Models.LocationDto
import io.reactivex.Completable
import io.reactivex.Single


class DriverContext(val context: Context) {


  private val httpConnectionString = "https://fc5c865e4722.ngrok.io/trackingServiceHub"
  private var hubConnection: HubConnection? = null
  lateinit var mHandler: Handler
  lateinit var listenerBuilder: AddListenDriver

  private val ON_INCOMING_TRIP = "ON_INCOMING_TRIP"
  private val ON_CANCEL_TRIP = "ON_CANCEL_TRIP"

  private val UPDATE_LOCATION_ACTION = "UpdateLocation"
  private val ACCEPT_TRIP_ACTION = "AcceptTrip"
  private val DECLINE_TRIP_ACTION = "DeclineTrip"
  private val IS_COMING_RESTAURANT = "IsComingRestaurant"

  fun hubBuilder(token: String?) {
    mHandler = Handler(Looper.getMainLooper())

    hubConnection = HubConnectionBuilder
      .create(httpConnectionString)
      .build()

    hubConnection?.on(ON_INCOMING_TRIP, { user: String ->
      mHandler.post(Runnable {
        listenerBuilder.onComingTrip()
      })
    }, String::class.java)

    hubConnection?.on(ON_CANCEL_TRIP, { user: String ->
      mHandler.post(Runnable {
        listenerBuilder.onDisconnect()
      })
    }, String::class.java)

    hubConnection?.onClosed {
      listenerBuilder.onDisconnect()
    }
  }

  @SuppressLint("CheckResult")
  fun connectToHub(): Completable? {
    if (!isConnectHub())
      return hubConnection?.start()
    return null
  }

  fun disconnectFromHub(): Completable? {
    if (isConnectHub())
      return hubConnection?.stop()
    return null
  }

  fun isConnectHub(): Boolean {
    if (hubConnection != null)
      return hubConnection?.connectionState == HubConnectionState.CONNECTED
    return false
  }

  fun sendAcceptTripAction(): Single<Boolean>? {
    if (isConnectHub())
      return hubConnection?.invoke(Boolean::class.java, ACCEPT_TRIP_ACTION,"Vũ Hoàng Uyên Nhi")
    return null
  }

  fun sendDeclineTripAction(): Single<Boolean>? {
    if (isConnectHub())
      return hubConnection?.invoke(Boolean::class.java, DECLINE_TRIP_ACTION)
    return null
  }

  fun addListenerDriver(listenerBuilder: AddListenDriver) {
    this.listenerBuilder = listenerBuilder
  }

  interface AddListenDriver {
    fun onComingTrip()
    fun onCancelTrip()
    fun onDisconnect()
  }

  fun updateDeviceLocation(location: Location): Single<LocationDto>? {
    if (isConnectHub()) {
      val newLocation = LocationDto.convertToLocationDto(location)
      return hubConnection?.invoke(LocationDto::class.java, UPDATE_LOCATION_ACTION, newLocation)
    }
    return null
  }

  fun sendIsComingToRestaurantAction(): Single<String>?{
    if (isConnectHub()) {
      return hubConnection?.invoke(String::class.java, IS_COMING_RESTAURANT, "Alo")
    }
    return null
  }

  fun sendArrivedRestaurantAction(): Single<Boolean>? {
    return null
  }

  fun sendConfirmTakeOrderAction(): Single<Boolean>? {
    return null
  }

  fun sendOrderBillAction(): Single<Boolean>? {
    return null
  }

  fun sendArrivedCustomerAction(): Single<Boolean>? {
    return null
  }

  fun sendConfirmDeliveryAction(): Single<Boolean>? {
    return null
  }
}