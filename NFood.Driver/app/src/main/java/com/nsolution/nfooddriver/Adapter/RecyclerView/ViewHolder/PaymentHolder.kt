package com.nsolution.nfooddriver.Adapter.RecyclerView.ViewHolder

import android.view.View
import android.widget.TextView
import com.nsolution.nfooddriver.R

class PaymentHolder(itemView : View) : BaseHolder(itemView){
  val paymentTitle = itemView.findViewById<TextView>(R.id.paymentTitle)
  val paymentDate = itemView.findViewById<TextView>(R.id.paymentDate)
  val paymentCost = itemView.findViewById<TextView>(R.id.paymentCost)
  val containerLayout = itemView.findViewById<View>(R.id.containerLayout)
}