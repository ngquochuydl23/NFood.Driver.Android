package com.nsolution.nfooddriver.Ui.Payment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nsolution.nfooddriver.Adapter.RecyclerView.Adapter.PaymentAdapter
import com.nsolution.nfooddriver.Models.ItemPaymentDto
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Base.BaseActivity
import kotlinx.android.synthetic.main.activity_payment.*

class Payment : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_payment)
    initialView()

    getListPayment()
  }

  private fun initialView() {
    getBackActionBar(header, getString(R.string.payment))
    listPayment.layoutManager = LinearLayoutManager(this)
  }

  private fun getListPayment() {

    val itemPayment = ItemPaymentDto().apply {
      id = 1
      paymentTitle = "NFood Payment Transfer from ng****** to +621312321"
      paymentCost = 50000.0
      paymentDate = "23/03/2020"
    }

    listPayment.adapter =
      PaymentAdapter(arrayListOf(itemPayment, itemPayment, itemPayment, itemPayment))
  }
}
