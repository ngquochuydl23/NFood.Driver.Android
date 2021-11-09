package com.nsolution.nfooddriver.Service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class EarningService : Service(){
  override fun onBind(intent: Intent?): IBinder? {
    return null
  }
}