package com.nsolution.nfooddriver.Ui.BottomSheet

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nsolution.nfooddriver.R

open class BaseBottomSheet : BottomSheetDialogFragment(){

  override fun getTheme(): Int {
    return R.style.BottomSheetDialogTheme
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    return BottomSheetDialog(requireContext(), theme)
  }

  open fun navigateTo(activity: Class<*>) {
    val intent = Intent(context, activity)
    startActivity(intent)
  }
}