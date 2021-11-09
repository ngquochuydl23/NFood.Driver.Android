package com.nsolution.nfooddriver.Adapter.RecyclerView.Adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.nsolution.nfooddriver.Adapter.RecyclerView.ViewHolder.OrderFoodHolder
import com.nsolution.nfooddriver.Models.ItemOrderFoodDto
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Utils.FormatCurrency

class OrderFoodAdapter(val listFoodOrder: ArrayList<ItemOrderFoodDto>?) :
  BaseAdapter<OrderFoodHolder>() {


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderFoodHolder {
    val view = getView(parent,viewType, R.layout.item_order_food)
    return OrderFoodHolder(view)
  }

  @SuppressLint("SetTextI18n")
  override fun onBindViewHolder(holder: OrderFoodHolder, position: Int) {
    val itemOrderFood = listFoodOrder?.get(position)

    holder.foodName.text = itemOrderFood?.foodName
    holder.option.text = itemOrderFood?.option
    holder.foodCost.text = FormatCurrency.FormatCurrencyVietNam(itemOrderFood?.foodCost)
    holder.quanlity.text = "x${itemOrderFood?.quanlity}"
  }

  override fun getItemCount(): Int {
    return getSizeList(listFoodOrder)
  }
}