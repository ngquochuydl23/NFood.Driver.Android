package com.nsolution.nfooddriver.Ui.Settings

import android.os.Bundle
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.SharedReferences.NavigationAppSharedPreferences
import com.nsolution.nfooddriver.SharedReferences.NavigationVoiceSharedPreferences
import com.nsolution.nfooddriver.Ui.Base.BaseActivity
import com.nsolution.nfooddriver.Utils.NavigationApp
import kotlinx.android.synthetic.main.activity_setup_navigation.*

class SetupNavigation : BaseActivity() {

  private var lastSelectNavigationApp : String? = null
  private var navigationAppSharedPreferences : NavigationAppSharedPreferences? = null
  private var navigationVoiceSharedPreferences : NavigationVoiceSharedPreferences? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_setup_navigation)

    navigationAppSharedPreferences = NavigationAppSharedPreferences(this)
    navigationVoiceSharedPreferences = NavigationVoiceSharedPreferences(this)

    initialView()
  }

  private fun initialView(){
    getBackActionBar(header,getString(R.string.setup_navigation))

    selectNavigationApp(navigationAppSharedPreferences?.getData())
    navigationVoiceSwitch.isChecked = navigationVoiceSharedPreferences?.getData() as Boolean

    nfoodRadioButton.setOnClickListener {
      selectNavigationApp(NavigationApp.NFOOD_NAVIGATION)
    }

    googlemapRadioButton.setOnClickListener {
      selectNavigationApp(NavigationApp.GOOGLE_MAP_NAVIGATION)
    }

    navigationVoiceSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
      navigationVoiceSharedPreferences?.setData(isChecked)
    }
  }

  private fun selectNavigationApp(navigationAppSelected : String?){
    if(navigationAppSelected == NavigationApp.NFOOD_NAVIGATION){
      nfoodRadioButton.isChecked = true
      googlemapRadioButton.isChecked = false
      lastSelectNavigationApp = NavigationApp.NFOOD_NAVIGATION
      navigationAppSharedPreferences?.setData(NavigationApp.NFOOD_NAVIGATION)
    } else {
      nfoodRadioButton.isChecked = false
      googlemapRadioButton.isChecked = true
      lastSelectNavigationApp = NavigationApp.GOOGLE_MAP_NAVIGATION
      navigationAppSharedPreferences?.setData(NavigationApp.GOOGLE_MAP_NAVIGATION)
    }
  }
}
