package com.nsolution.nfooddriver.Ui.History

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nsolution.nfooddriver.Adapter.RecyclerView.Adapter.TripHistoryAdapter
import com.nsolution.nfooddriver.Models.ItemTripHistoryDto
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Base.BaseActivity
import kotlinx.android.synthetic.main.activity_trip_history.*

class TripHistory : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_trip_history)

    initialView()
  }

  private fun initialView() {
    getBackActionBar(header, getString(R.string.trip_history))

    listHistoryTrip.layoutManager = LinearLayoutManager(this)
    historyTrip()
  }

  private fun historyTrip(){
    val itemHistory = ItemTripHistoryDto().apply {
      tripId = 1
      tripDateTime = "23/03/2020, 3:15 PM"
      tripVehical = "Honda Wave Rs"
      tripEarnings = 1200.0
    }

    listHistoryTrip.adapter = TripHistoryAdapter(arrayListOf(itemHistory,itemHistory,itemHistory,itemHistory))
  }
}
