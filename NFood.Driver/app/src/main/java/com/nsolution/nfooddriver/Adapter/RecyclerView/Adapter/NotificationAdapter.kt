package com.nsolution.nfooddriver.Adapter.RecyclerView.Adapter

import android.view.ViewGroup
import com.nsolution.nfooddriver.Adapter.RecyclerView.ViewHolder.NotificationHolder
import com.nsolution.nfooddriver.Models.ItemNotificationDto
import com.nsolution.nfooddriver.R

class NotificationAdapter(val listNotification: ArrayList<ItemNotificationDto>?) : BaseAdapter<NotificationHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
    val view = getView(parent,viewType, R.layout.item_notification)
    return NotificationHolder(view)
  }

  override fun onBindViewHolder(holder: NotificationHolder, position: Int) {
    val itemNotification = listNotification?.get(position)

    holder.title.text = itemNotification?.title
    holder.subtitle.text = itemNotification?.subtitle
    holder.createdOn.text = itemNotification?.createdOn
    holder.containerLayout.setOnClickListener {

    }
  }

  override fun getItemCount(): Int {
    return getSizeList(listNotification)
  }
}