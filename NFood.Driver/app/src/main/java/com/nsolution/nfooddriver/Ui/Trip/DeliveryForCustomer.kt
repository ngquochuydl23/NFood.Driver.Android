package com.nsolution.nfooddriver.Ui.Trip

import android.os.Bundle
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Base.BaseActivity
import com.nsolution.nfooddriver.Ui.HomeScreen.HomeScreen
import kotlinx.android.synthetic.main.activity_delivery_for_customer.*

class DeliveryForCustomer : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_delivery_for_customer)
    initialView()
  }

  private fun initialView(){
    getBackActionBar(header,getString(R.string.delivery_for_customer))

    confirmButton.setOnClickListener {
      confirmFinishOrder()
    }
  }

  private fun confirmFinishOrder(){
    navigateTo(HomeScreen::class.java)
    finish()
    Trip.activity.finish()
  }
}
