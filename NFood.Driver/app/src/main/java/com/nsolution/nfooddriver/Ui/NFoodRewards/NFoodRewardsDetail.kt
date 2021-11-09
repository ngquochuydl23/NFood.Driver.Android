package com.nsolution.nfooddriver.Ui.NFoodRewards

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nsolution.nfooddriver.Adapter.RecyclerView.Adapter.RewardBenefitAdapter
import com.nsolution.nfooddriver.Models.ItemRewardBenefit
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Base.BaseActivity
import kotlinx.android.synthetic.main.activity_nfood_reward_detail.*

class NFoodRewardsDetail : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_nfood_reward_detail)

    initialView()
    getRewardInformation()
  }

  private fun initialView() {
    collapsingToolbarLayout("NFood Diamond")
    listRewardBenefits.layoutManager = LinearLayoutManager(this)
  }

  private fun getRewardInformation() {

    val itemRewardBenefit = ItemRewardBenefit().apply {
      id = 1
      benefitTitle = "Premimum Support"
      benefitSubtitle = "Enjoy dedicated 24/7 phone support from experienced support agents."
    }

    listRewardBenefits.adapter = RewardBenefitAdapter(
      arrayListOf(
        itemRewardBenefit,
        itemRewardBenefit,
        itemRewardBenefit,
        itemRewardBenefit
      )
    )
  }
}
