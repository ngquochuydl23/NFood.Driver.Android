package com.nsolution.nfooddriver.Ui.Trip

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nsolution.nfooddriver.Adapter.RecyclerView.Adapter.OrderFoodAdapter
import com.nsolution.nfooddriver.Models.ItemOrderFoodDto
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Base.BaseActivity
import kotlinx.android.synthetic.main.activity_confirm_take_order.*

class ConfirmTakeOrder : BaseActivity() {

  companion object {
    lateinit var activity: Activity
  }

  init {
    activity = this
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_confirm_take_order)

    initialView()
    getOrderFood()
  }

  private fun initialView() {
    getBackActionBar(header, getString(R.string.confirm_take_order))
    listOrderFood.layoutManager = LinearLayoutManager(this)

    confirmTakeOrder.setOnClickListener {
      navigateTo(TakePhotoOrder::class.java)
    }
  }

  private fun getOrderFood() {
    val itemOrderFood = ItemOrderFoodDto().apply {
      id = 1
      foodName = "Trà Sữa Okinawa"
      option = "100% Kem\n50% Đường"
      foodCost = 40000.0
      quanlity = 1
    }
    listOrderFood.adapter =
      OrderFoodAdapter(arrayListOf(itemOrderFood, itemOrderFood, itemOrderFood, itemOrderFood))
  }
}
