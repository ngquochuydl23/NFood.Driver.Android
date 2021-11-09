package com.nsolution.nfooddriver.Adapter.RecyclerView.Adapter

import android.view.ViewGroup
import com.nsolution.nfooddriver.Adapter.RecyclerView.ViewHolder.RiderComplimentHolder
import com.nsolution.nfooddriver.Models.ItemRiderComplimentDto
import com.nsolution.nfooddriver.R

class RiderComplimentAdapter(val listCompliment: ArrayList<ItemRiderComplimentDto>?) :
  BaseAdapter<RiderComplimentHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiderComplimentHolder {
    val view = getView(parent,viewType, R.layout.item_rider_compliments)
    return RiderComplimentHolder(view)
  }

  override fun onBindViewHolder(holder: RiderComplimentHolder, position: Int) {
    val itemCompliment = listCompliment?.get(position)

    holder.numberCompliment.text = itemCompliment?.numberCompliment.toString()
    holder.title.text = itemCompliment?.title
  }

  override fun getItemCount(): Int {
    return getSizeList(listCompliment)
  }
}