package com.nsolution.nfooddriver.Ui.Earnings

import android.os.Bundle
import com.nsolution.nfooddriver.Adapter.Chart.EarningsChart
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Base.BaseActivity
import com.nsolution.nfooddriver.Ui.NFoodRewards.NFoodRewardsDetail
import kotlinx.android.synthetic.main.activity_earnings.*

class Earnings : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_earnings)

    initialView()
    getSaleChart()
  }

  private fun initialView() {
    getBackActionBar(header, getString(R.string.earnings))

    cashOutButton.setOnClickListener {

    }

    seeDetailRewards.setOnClickListener {
      navigateTo(NFoodRewardsDetail::class.java)
    }
  }

  private fun getSaleChart() {
    val dataEarningsChart = arrayListOf(200f, 10f, 10f, 10f, 200f, 1000f, 10f)
    EarningsChart(earningChart, dataEarningsChart)
  }
}
