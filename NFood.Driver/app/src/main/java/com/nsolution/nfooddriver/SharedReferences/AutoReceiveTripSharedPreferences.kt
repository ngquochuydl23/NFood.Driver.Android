package com.nsolution.nfooddriver.SharedReferences

import android.content.Context

class AutoReceiveTripSharedPreferences(context: Context?) : BaseSharedPreferences<Boolean>(context) {

  private val AUTO_RECEIVE_TRIP = "AUTO_RECEIVE_TRIP"

  override fun setName(): String = AUTO_RECEIVE_TRIP

  override fun getData(): Boolean? {
    return sharedPreferences?.getBoolean(AUTO_RECEIVE_TRIP, false)
  }

  override fun setData(data: Boolean) {
    editor.putBoolean(AUTO_RECEIVE_TRIP, data).commit()
  }

  override fun deleteData() {
    editor.clear().apply()
  }
}