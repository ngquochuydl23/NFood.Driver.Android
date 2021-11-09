package com.nsolution.nfooddriver.SharedReferences

import android.content.Context
import com.google.gson.Gson
import com.nsolution.nfooddriver.Models.IncomingSound
import com.nsolution.nfooddriver.R

class IncomingSoundSharedPreferences(context: Context?) :
  BaseSharedPreferences<IncomingSound?>(context) {

  private val SOUND_NAME = "SOUND_NAME"

  override fun setName(): String = SOUND_NAME

  override fun getData(): IncomingSound? {
    val defaultSound = IncomingSound(1, R.raw.sound_default)
    val dataJson = sharedPreferences?.getString(SOUND_NAME, Gson().toJson(defaultSound))
    return Gson().fromJson(dataJson, IncomingSound::class.java)
  }

  override fun setData(data: IncomingSound?) {
    if (data != null) {
      val dataJson = Gson().toJson(data)
      editor.putString(SOUND_NAME, dataJson).commit()
    }
  }

  override fun deleteData() {
    editor.clear().apply()
  }
}