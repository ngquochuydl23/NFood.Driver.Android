package com.nsolution.nfooddriver.SharedReferences

import android.content.Context

class SaveTokenSharedPreferences(context: Context?) : BaseSharedPreferences<String?>(context) {

  private val TOKEN_NAME = "TOKEN_NAME"

  override fun setName(): String = TOKEN_NAME

  override fun getData(): String? {
    return sharedPreferences?.getString(TOKEN_NAME, null)
  }

  override fun setData(data: String?) {
    editor.putString(TOKEN_NAME, data).commit()
  }

  override fun deleteData() {
    editor.clear().apply()
  }
}