package com.nsolution.nfooddriver.Receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.nsolution.nfooddriver.Service.DriverService

class BootReceiver : BroadcastReceiver() {

  override fun onReceive(context: Context, intent: Intent) {
    val serviceIntent = Intent(context, DriverService::class.java)

    if (intent.action.equals(Intent.ACTION_SHUTDOWN, true)) {

      context.stopService(serviceIntent)
    } else if (intent.action.equals(Intent.ACTION_BOOT_COMPLETED, true)) {
      context.startService(serviceIntent)
    } else if (intent.action.equals(Intent.ACTION_MY_PACKAGE_REPLACED, true)) {
      context.startService(serviceIntent)
    }
  }
}
