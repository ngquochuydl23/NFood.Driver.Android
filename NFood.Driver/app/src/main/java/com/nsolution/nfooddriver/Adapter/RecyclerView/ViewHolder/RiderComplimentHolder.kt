package com.nsolution.nfooddriver.Adapter.RecyclerView.ViewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.nsolution.nfooddriver.R

class RiderComplimentHolder(itemView: View) : BaseHolder(itemView) {
  val containerLayout = itemView.findViewById<View>(R.id.containerLayout)
  val image = itemView.findViewById<ImageView>(R.id.image)
  val title = itemView.findViewById<TextView>(R.id.title)
  val numberCompliment = itemView.findViewById<TextView>(R.id.numberCompliment)
}