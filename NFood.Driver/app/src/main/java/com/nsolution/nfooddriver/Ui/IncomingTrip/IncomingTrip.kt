package com.nsolution.nfooddriver.Ui.IncomingTrip


import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.Vibrator
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Service.DriverService
import com.nsolution.nfooddriver.Ui.Base.BaseActivity
import com.nsolution.nfooddriver.Ui.Trip.Trip
import kotlinx.android.synthetic.main.activity_incoming_trip.*


class IncomingTrip : BaseActivity() {


  private var boundService: Boolean? = null
  private var driverService: DriverService? = null
  private var ringtone: Ringtone? = null
  private var vibrator: Vibrator? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_incoming_trip)

    setupSoundNotification()

    acceptButton.setOnClickListener {
      stopRingtoneAndVibrate()
      sendAcceptTripAction()
    }

    declineButton.setOnClickListener {
      sendDeclineTripAction()
      stopRingtoneAndVibrate()
    }
  }

  override fun onStart() {
    super.onStart()
    blindService()
  }

  override fun onStop() {
    super.onStop()
    unblindService()
    boundService = false
  }

  override fun navigateTo(activity: Class<*>) {
    val intent = Intent(this, Trip::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(intent)
    finish()
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
    }

    override fun onServiceDisconnected(name: ComponentName?) {
      boundService = false
    }
  }

  private fun setupSoundNotification() {
    val path = Uri.parse("android.resource://$packageName/" + R.raw.sound_default)
    RingtoneManager.setActualDefaultRingtoneUri(
      applicationContext, RingtoneManager.TYPE_RINGTONE,
      path
    )

    ringtone = RingtoneManager.getRingtone(applicationContext, path)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
      ringtone?.isLooping = true
    ringtone?.play()

    val pattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
    vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    vibrator?.vibrate(pattern, 4)
  }

  private fun stopRingtoneAndVibrate() {
    ringtone?.stop()
    vibrator?.cancel()
  }


  private fun sendAcceptTripAction() {
    driverService?.sendAcceptTripAction()
    navigateTo(Trip::class.java)
  }

  private fun sendDeclineTripAction() {
    driverService?.sendDeclineTripAction()
    finish()
  }
}
