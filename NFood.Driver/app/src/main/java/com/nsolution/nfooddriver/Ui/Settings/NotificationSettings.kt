package com.nsolution.nfooddriver.Ui.Settings

import android.os.Bundle
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Base.BaseActivity
import kotlinx.android.synthetic.main.activity_notification_settings.*

class NotificationSettings : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_notification_settings)
    initialView()
  }

  private fun initialView() {
    getBackActionBar(header, getString(R.string.notification_setting))

    setUpSoundLayoutClick.setOnClickListener {
      navigateTo(SetupIncomingSound::class.java)
    }
  }
}
