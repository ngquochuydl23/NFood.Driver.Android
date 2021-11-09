package com.nsolution.nfooddriver.Ui.Profile


import android.os.Bundle
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Base.BaseActivity
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfile : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_edit_profile)
    initialView()
  }

  private fun initialView(){
    getBackActionBar(header,getString(R.string.edit_profile))
  }
}
