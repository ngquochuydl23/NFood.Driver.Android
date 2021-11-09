package com.nsolution.nfooddriver.Ui.Profile

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nsolution.nfooddriver.Adapter.RecyclerView.Adapter.CustomerReviewAdapter
import com.nsolution.nfooddriver.Adapter.RecyclerView.Adapter.RiderComplimentAdapter
import com.nsolution.nfooddriver.Models.ItemCustomerReviewDto
import com.nsolution.nfooddriver.Models.ItemRiderComplimentDto
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Base.BaseActivity
import kotlinx.android.synthetic.main.activity_profile.*

class Profile : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_profile)

    initialView()
    getRiderCompliment()

    getCustomerReview()
  }

  private fun initialView() {
    getBackActionBar(header, getString(R.string.profile))
    listRiderCompliment.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
    listCommentOfCustomer.layoutManager = LinearLayoutManager(this)

    editProfileButton.setOnClickListener {
      navigateTo(EditProfile::class.java)
    }
  }

  private fun getRiderCompliment() {
    val item = ItemRiderComplimentDto().apply {
      id = 1
      title = "Best Driver"
      numberCompliment = 20
    }
    listRiderCompliment.adapter = RiderComplimentAdapter(arrayListOf(item, item, item, item, item))
  }

  private fun getCustomerReview() {
    val itemReview = ItemCustomerReviewDto().apply {
      id = 1
      commentContent =
        "Really nice driver with a lot of interesting stories. Thanks for i've ever sat inside!"
      createdOn = "23 March"
    }
    listCommentOfCustomer.adapter = CustomerReviewAdapter(arrayListOf(itemReview,itemReview,itemReview))

  }
}
