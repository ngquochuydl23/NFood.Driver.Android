package com.nsolution.nfooddriver.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemPaymentDto {
  @SerializedName("id")
  @Expose
  var id: Int? = null

  @SerializedName("paymentTitle")
  @Expose
  var paymentTitle : String? = null

  @SerializedName("paymentDate")
  @Expose
  var paymentDate : String? = null

  @SerializedName("paymentCost")
  @Expose
  var paymentCost : Double? = null
}