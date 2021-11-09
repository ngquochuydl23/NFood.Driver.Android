package com.nsolution.nfooddriver.Ui.Helps

import android.os.Bundle
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Base.BaseActivity
import kotlinx.android.synthetic.main.activity_help_center.*

class HelpCenter : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_help_center)

    initialView()
  }

  private fun initialView() {
    getBackActionBar(header, getString(R.string.helps_center))
  }
}
