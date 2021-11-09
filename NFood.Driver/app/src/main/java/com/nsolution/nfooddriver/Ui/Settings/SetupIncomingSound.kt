package com.nsolution.nfooddriver.Ui.Settings

import android.os.Bundle
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Base.BaseActivity
import kotlinx.android.synthetic.main.activity_setup_incoming_sound.*

class SetupIncomingSound : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_setup_incoming_sound)

    initialView()
  }

  private fun initialView(){
    getBackActionBar(header,getString(R.string.setup_incoming_sound))
  }
}
