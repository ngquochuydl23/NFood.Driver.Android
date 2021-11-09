package com.nsolution.nfooddriver.Adapter.RecyclerView.ViewHolder

import android.view.View
import android.widget.TextView
import com.nsolution.nfooddriver.R

class RewardBenefitHolder(itemView : View) : BaseHolder(itemView) {
  val containerLayout = itemView.findViewById<View>(R.id.containerLayout)
  val benefitTitle = itemView.findViewById<TextView>(R.id.benefitTitle)
  val benefitSubtitle = itemView.findViewById<TextView>(R.id.benefitSubtitle)
}