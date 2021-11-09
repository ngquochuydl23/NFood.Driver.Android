package com.nsolution.nfooddriver.SharedReferences

import android.content.Context

class NavigationVoiceSharedPreferences(context: Context?) : BaseSharedPreferences<Boolean>(context) {

  private val NAVIGATION_VOICE = "NAVIGATION_VOICE"

  override fun setName(): String = NAVIGATION_VOICE

  override fun getData(): Boolean? {
    return sharedPreferences?.getBoolean(NAVIGATION_VOICE, true)
  }

  override fun setData(data: Boolean) {
    editor.putBoolean(NAVIGATION_VOICE, data).commit()
  }

  override fun deleteData() {
    editor.clear().apply()
  }
}