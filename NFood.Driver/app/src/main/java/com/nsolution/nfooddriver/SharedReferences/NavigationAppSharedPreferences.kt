package com.nsolution.nfooddriver.SharedReferences

import android.content.Context
import com.nsolution.nfooddriver.Utils.NavigationApp

class NavigationAppSharedPreferences(context: Context?) : BaseSharedPreferences<String>(context) {

  private val NAVIGATION_APP = "NAVIGATION_APP"

  override fun setName(): String = NAVIGATION_APP

  override fun getData(): String? {
    return sharedPreferences?.getString(NAVIGATION_APP, NavigationApp.GOOGLE_MAP_NAVIGATION)
  }

  override fun setData(data: String) {
    editor.putString(NAVIGATION_APP, data).commit()
  }

  override fun deleteData() {
    editor.clear().apply()
  }
}