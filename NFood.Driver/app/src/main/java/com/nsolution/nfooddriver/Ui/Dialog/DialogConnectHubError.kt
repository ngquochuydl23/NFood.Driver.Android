package com.nsolution.nfooddriver.Ui.Dialog

import android.app.Activity
import android.widget.Button
import com.nsolution.nfooddriver.R

class DialogConnectHubError(val activity: Activity) : BaseDialog(activity){

  private var listenerBuilder: DialogConnectHubListener? = null

  override fun onBlindDialog() {
    super.onBlindDialog()
    setContentView(R.layout.layout_dialog_connect_error)

    val tryAgainButton = view?.findViewById<Button>(R.id.tryAgainButton)

    tryAgainButton?.setOnClickListener {
      listenerBuilder?.tryAgainAction()
    }
  }

  fun addListener(listenerBuilder: DialogConnectHubListener) {
    this.listenerBuilder = listenerBuilder
  }

  interface DialogConnectHubListener{
    fun tryAgainAction()
  }
}