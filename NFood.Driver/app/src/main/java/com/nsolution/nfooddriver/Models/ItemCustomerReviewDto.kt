package com.nsolution.nfooddriver.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemCustomerReviewDto {
  @SerializedName("id")
  @Expose
  var id : Int? = null

  @SerializedName("commentContent")
  @Expose
  var commentContent : String? = null

  @SerializedName("createdOn")
  @Expose
  var createdOn : String? = null
}