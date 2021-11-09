package com.nsolution.nfooddriver.Models

import com.google.gson.annotations.SerializedName

data class IncomingSound(
  @SerializedName("id")
  val id: Int?,
  @SerializedName("soundRes")
  val soundRes: Int?
)