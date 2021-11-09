package com.nsolution.nfooddriver.Ui.Settings

import android.os.Bundle
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Base.BaseActivity
import kotlinx.android.synthetic.main.activity_language_settings.*

class LanguageSettings : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_language_settings)
    initialView()
  }

  private fun initialView() {
    getBackActionBar(header, getString(R.string.language_setting))
  }
}
