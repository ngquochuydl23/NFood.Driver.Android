package com.nsolution.nfooddriver.Adapter.RecyclerView.Adapter

import android.view.ViewGroup
import com.nsolution.nfooddriver.Adapter.RecyclerView.ViewHolder.RewardBenefitHolder
import com.nsolution.nfooddriver.Models.ItemRewardBenefit
import com.nsolution.nfooddriver.R

class RewardBenefitAdapter(val listBenefit: ArrayList<ItemRewardBenefit>?) :
  BaseAdapter<RewardBenefitHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardBenefitHolder {
    val view = getView(parent, viewType, R.layout.item_reward_benefit)
    return RewardBenefitHolder(view)
  }

  override fun onBindViewHolder(holder: RewardBenefitHolder, position: Int) {
    val itemRewardBenefit = listBenefit?.get(position)

    holder.benefitTitle.text = itemRewardBenefit?.benefitTitle
    holder.benefitSubtitle.text = itemRewardBenefit?.benefitSubtitle
    holder.containerLayout.setOnClickListener {

    }
  }

  override fun getItemCount(): Int {
    return getSizeList(listBenefit)
  }
}