package com.nsolution.nfooddriver.Ui.Trip


import android.app.Activity
import android.os.Bundle
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Base.BaseActivity
import kotlinx.android.synthetic.main.activity_take_order.*

class TakeOrder : BaseActivity() {

  companion object {
    lateinit var activity: Activity
  }

  init {
    activity = this
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_take_order)

    initialView()
  }

  private fun initialView(){
    getBackActionBar(header,getString(R.string.take_order))

    takeOrderButton.setOnClickListener {
      navigateTo(ConfirmTakeOrder::class.java)
    }
  }
}
