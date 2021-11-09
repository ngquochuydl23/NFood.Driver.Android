package com.nsolution.nfooddriver.Ui.Notification


import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nsolution.nfooddriver.Adapter.RecyclerView.Adapter.NotificationAdapter
import com.nsolution.nfooddriver.Models.ItemNotificationDto
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Base.BaseActivity
import kotlinx.android.synthetic.main.activity_notification.*

class Notification : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_notification)
    initialView()

    getListNotification()
  }

  private fun initialView() {
    getBackActionBar(header, getString(R.string.notification))

    listNotification.layoutManager = LinearLayoutManager(this)
  }

  private fun getListNotification(){
    val itemNotification = ItemNotificationDto().apply {
      id = 1
      title = "You have received 5 new compliments"
      subtitle = "Tap and see detail notification"
      createdOn = "23/03/2020"
    }
    listNotification.adapter = NotificationAdapter(arrayListOf(itemNotification,itemNotification,itemNotification,itemNotification,itemNotification))
  }
}
