package com.nsolution.nfooddriver.Adapter.RecyclerView.Adapter

import android.content.Intent
import android.view.ViewGroup
import com.nsolution.nfooddriver.Adapter.RecyclerView.ViewHolder.PaymentHolder
import com.nsolution.nfooddriver.Models.ItemPaymentDto
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Payment.PaymentDetail
import com.nsolution.nfooddriver.Utils.FormatCurrency

class PaymentAdapter(val listPayment: ArrayList<ItemPaymentDto>?) : BaseAdapter<PaymentHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentHolder {
    val view = getView(parent, viewType, R.layout.item_payment)
    return PaymentHolder(view)
  }

  override fun onBindViewHolder(holder: PaymentHolder, position: Int) {
    val itemPayment = listPayment?.get(position)

    holder.paymentTitle.text = itemPayment?.paymentTitle
    holder.paymentDate.text = itemPayment?.paymentDate
    holder.paymentCost.text = FormatCurrency.FormatCurrencyVietNam(itemPayment?.paymentCost)

    holder.containerLayout.setOnClickListener {
      val intent = Intent(context, PaymentDetail::class.java)
      context.startActivity(intent)
    }
  }

  override fun getItemCount(): Int {
    return getSizeList(listPayment)
  }
}