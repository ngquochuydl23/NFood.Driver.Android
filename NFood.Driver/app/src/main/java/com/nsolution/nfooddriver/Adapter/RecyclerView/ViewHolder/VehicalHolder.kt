package com.nsolution.nfooddriver.Adapter.RecyclerView.ViewHolder

import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import com.nsolution.nfooddriver.R

class VehicalHolder(itemView : View) : BaseHolder(itemView) {
  val vehicalName = itemView.findViewById<TextView>(R.id.vehicalName)
  val vehicalLicense = itemView.findViewById<TextView>(R.id.vehicalLicense)
  val vehicalIsUsed = itemView.findViewById<RadioButton>(R.id.vehicalIsUsed)
  val containerLayout = itemView.findViewById<View>(R.id.containerLayout)
}