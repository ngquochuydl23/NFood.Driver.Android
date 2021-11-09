package com.nsolution.nfooddriver.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemOrderFoodDto {
  @SerializedName("id")
  @Expose
  var id: Int? = null

  @SerializedName("foodName")
  @Expose
  var foodName: String? = null

  @SerializedName("option")
  @Expose
  var option: String? = null

  @SerializedName("quanlity")
  @Expose
  var quanlity: Int? = null

  @SerializedName("foodCost")
  @Expose
  var foodCost: Double? = null
}