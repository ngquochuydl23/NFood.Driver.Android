package com.nsolution.nfooddriver.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemTripHistoryDto {
  @SerializedName("TripId")
  @Expose
  var tripId : Int? = null

  @SerializedName("tripDateTime")
  @Expose
  var tripDateTime : String? = null

  @SerializedName("tripVehical")
  @Expose
  var tripVehical : String? = null

  @SerializedName("tripEarnings")
  @Expose
  var tripEarnings : Double? = null
}