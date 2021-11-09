package com.nsolution.nfooddriver.Ui.Settings

import android.os.Bundle
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Base.BaseActivity
import kotlinx.android.synthetic.main.activity_settings.*

class Settings : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_settings)

    initialView()
  }

  private fun initialView() {
    getBackActionBar(header, getString(R.string.settings))

    setUpTripLayoutClick.setOnClickListener {
      navigateTo(TripSettings::class.java)
    }

    setUpNotificationLayoutClick.setOnClickListener {
      navigateTo(NotificationSettings::class.java)
    }

    setUpLanguageLayoutClick.setOnClickListener {
      navigateTo(LanguageSettings::class.java)
    }
  }
}
