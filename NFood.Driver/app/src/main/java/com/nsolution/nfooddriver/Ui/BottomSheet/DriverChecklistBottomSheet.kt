package com.nsolution.nfooddriver.Ui.BottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nsolution.nfooddriver.R
import kotlinx.android.synthetic.main.layout_bottom_sheet_before_you_drive.*

class DriverChecklistBottomSheet : BaseBottomSheet() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return LayoutInflater.from(context)
      .inflate(R.layout.layout_bottom_sheet_before_you_drive, container)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    okButton.setOnClickListener {
      dismiss()
    }
  }
}