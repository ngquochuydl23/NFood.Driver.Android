package com.nsolution.nfooddriver.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemVehicalDto {
  @SerializedName("id")
  @Expose
  var id : Int? = null

  @SerializedName("vehicalName")
  @Expose
  var vehicalName : String? = null

  @SerializedName("vehicalLicense")
  @Expose
  var vehicalLicense : String? = null

  @SerializedName("vehicalIsUsed")
  @Expose
  var vehicalIsUsed : Boolean? = null
}