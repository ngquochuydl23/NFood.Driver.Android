package com.nsolution.nfooddriver.Adapter.RecyclerView.ViewHolder

import android.view.View
import android.widget.TextView
import com.nsolution.nfooddriver.R

class OrderFoodHolder(itemView : View) : BaseHolder(itemView) {
  val containerLayout = itemView.findViewById<View>(R.id.containerLayout)
  val foodName = itemView.findViewById<TextView>(R.id.foodName)
  val option = itemView.findViewById<TextView>(R.id.option)
  val foodCost = itemView.findViewById<TextView>(R.id.foodCost)
  val quanlity = itemView.findViewById<TextView>(R.id.quanlity)
}