package com.nsolution.nfooddriver.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemRiderComplimentDto {
  @SerializedName("id")
  @Expose
  var id : Int? = null
  @SerializedName("title")
  @Expose
  var title : String? = null
  @SerializedName("image")
  @Expose
  var image : String? = null
  @SerializedName("numberCompliment")
  @Expose
  var numberCompliment : Int? = null
}