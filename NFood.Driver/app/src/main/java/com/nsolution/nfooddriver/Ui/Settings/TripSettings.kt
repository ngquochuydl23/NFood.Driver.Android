package com.nsolution.nfooddriver.Ui.Settings


import android.os.Bundle
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.SharedReferences.AutoReceiveTripSharedPreferences
import com.nsolution.nfooddriver.Ui.Base.BaseActivity
import kotlinx.android.synthetic.main.activity_trip_settings.*

class TripSettings : BaseActivity() {

  private var autoReceiveTripSharedPreferences : AutoReceiveTripSharedPreferences? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_trip_settings)

    autoReceiveTripSharedPreferences = AutoReceiveTripSharedPreferences(this)

    initialView()
  }

  private fun initialView() {
    getBackActionBar(header, getString(R.string.trip_settings))
    autoReceiveTripSwitch.isChecked = autoReceiveTripSharedPreferences?.getData() as Boolean

    autoReceiveTripSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
      autoReceiveTripSharedPreferences?.setData(isChecked)
    }

    setupNavigationLayoutClick.setOnClickListener {
      navigateTo(SetupNavigation::class.java)
    }
  }
}
