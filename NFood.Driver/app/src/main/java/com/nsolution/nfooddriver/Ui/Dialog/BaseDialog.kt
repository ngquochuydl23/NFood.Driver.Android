package com.nsolution.nfooddriver.Ui.Dialog

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import androidx.annotation.LayoutRes
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView

open class BaseDialog(val context: Context?) {

  var dialog: MaterialDialog? = null

  var view: View? = null

  fun setContentView(@LayoutRes layout: Int) {
    dialog?.customView(layout, dialogWrapContent = true, noVerticalPadding = true)
    this.view = dialog?.getCustomView()
  }

  fun showDialog() {
    dialog?.show()
  }

  open fun setCanceledOnTouchOutside(isCancel: Boolean) {
    dialog?.cancelOnTouchOutside(false)
  }

  @SuppressLint("ResourceType")
  open fun onBlindDialog() {
    dialog = MaterialDialog(context!!)
    dialog?.cornerRadius(10f)
  }

  open fun dismiss() {
    dialog?.dismiss()
  }
}