package com.nsolution.nfooddriver.Adapter.RecyclerView.Adapter

import android.view.ViewGroup
import com.nsolution.nfooddriver.Adapter.RecyclerView.ViewHolder.VehicalHolder
import com.nsolution.nfooddriver.Models.ItemVehicalDto
import com.nsolution.nfooddriver.R

class VehicalAdapter(val listVehical : ArrayList<ItemVehicalDto>?) : BaseAdapter<VehicalHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicalHolder {
    val view = getView(parent,viewType, R.layout.item_vehical)
    return VehicalHolder(view)
  }

  override fun onBindViewHolder(holder: VehicalHolder, position: Int) {
    val itemVehical = listVehical?.get(position)

    holder.vehicalName.text = itemVehical?.vehicalName
    holder.vehicalLicense.text = itemVehical?.vehicalLicense
    holder.vehicalIsUsed.isChecked = itemVehical?.vehicalIsUsed!!
    holder.containerLayout.setOnClickListener {

    }
  }

  override fun getItemCount(): Int {
    return getSizeList(listVehical)
  }
}