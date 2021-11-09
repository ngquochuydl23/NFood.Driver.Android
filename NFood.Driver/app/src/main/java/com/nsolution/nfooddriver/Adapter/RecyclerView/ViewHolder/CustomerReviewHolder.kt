package com.nsolution.nfooddriver.Adapter.RecyclerView.ViewHolder

import android.view.View
import android.widget.TextView
import com.nsolution.nfooddriver.R

class CustomerReviewHolder(itemView : View) : BaseHolder(itemView){
  val createdOn = itemView.findViewById<TextView>(R.id.createdOn)
  val commentContent = itemView.findViewById<TextView>(R.id.commentContent)
}