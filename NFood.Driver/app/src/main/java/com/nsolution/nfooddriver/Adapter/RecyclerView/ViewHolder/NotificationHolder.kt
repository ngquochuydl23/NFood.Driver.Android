package com.nsolution.nfooddriver.Adapter.RecyclerView.ViewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.nsolution.nfooddriver.R

class NotificationHolder(itemView: View) : BaseHolder(itemView) {
  val title = itemView.findViewById<TextView>(R.id.title)
  val avatar = itemView.findViewById<ImageView>(R.id.avatar)
  val subtitle = itemView.findViewById<TextView>(R.id.subtitle)
  val createdOn = itemView.findViewById<TextView>(R.id.createdOn)
  val containerLayout = itemView.findViewById<View>(R.id.containerLayout)
}