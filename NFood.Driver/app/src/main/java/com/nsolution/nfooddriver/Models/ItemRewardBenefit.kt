package com.nsolution.nfooddriver.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemRewardBenefit {
  @SerializedName("id")
  @Expose
  var id : Int? = null

  @SerializedName("benefitTitle")
  @Expose
  var benefitTitle : String? = null

  @SerializedName("benefitSubtitle")
  @Expose
  var benefitSubtitle : String? = null
}