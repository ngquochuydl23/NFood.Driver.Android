package com.nsolution.nfooddriver.Adapter.RecyclerView.ViewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.nsolution.nfooddriver.R

class TripHistoryHolder(itemView: View) : BaseHolder(itemView) {
  val tripMapImage = itemView.findViewById<ImageView>(R.id.tripMapImage)
  val tripDateTime = itemView.findViewById<TextView>(R.id.tripDateTime)
  val tripEarnings = itemView.findViewById<TextView>(R.id.tripEarnings)
  val tripVehical = itemView.findViewById<TextView>(R.id.tripVehical)
  val containerLayout = itemView.findViewById<View>(R.id.containerLayout)
}