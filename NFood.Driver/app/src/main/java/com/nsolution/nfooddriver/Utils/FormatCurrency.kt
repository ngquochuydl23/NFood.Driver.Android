package com.nsolution.nfooddriver.Utils

import java.text.NumberFormat
import java.util.*

class FormatCurrency {

  companion object {

    fun FormatCurrencyVietNam(money: Double?): String {
      val locale = Locale("vi", "VN")
      val format: NumberFormat = NumberFormat.getCurrencyInstance(locale)
      if(money != null )
        return format.format(money)
      return ""
    }
  }

}